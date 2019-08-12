package com.dd.githubapp.service.core

import android.text.TextUtils
import android.util.Base64
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
class AppRetrofit {

    private val baseUrl = "https://api.github.com"

    fun createRetrofit() {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(2000, TimeUnit.MILLISECONDS)
            .addInterceptor(BaseInterceptor())
            .addNetworkInterceptor(NetworkInterceptor())
            .build()
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        fun <S> createRetrofit(serviceClass: Class<S>, username: String, password: String): S {
            val credentials = "$username:$password"
            val basic = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
            Log.d("AppRetrofit", "basic=$basic")
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(2000, TimeUnit.MILLISECONDS)
                .addInterceptor(BaseAuthInterceptor(basic))
                .build()
            val builder = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return builder.create(serviceClass)
        }
    }

    private class BaseAuthInterceptor(private val basic: String) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val origin = chain.request()
            val requestBuilder = origin.newBuilder()
                .header("Authorization", basic)
                .header("Accept", "application/vnd.github.v3+json")
                .method(origin.method(), origin.body())
            val request = requestBuilder.build()
            return chain.proceed(request)
        }
    }

    private class BaseInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            // TODO   处理拦截事件
            return chain.proceed(request)
        }
    }

    private class NetworkInterceptor : Interceptor {
        val cacheMaxAge = 4 * 7 * 24 * 60 * 60
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val originResponse = chain.proceed(request)
            var requestCacheControl = request.cacheControl().toString()
            val forceNetwork = request.header("forceNetWork")
            if (!TextUtils.isEmpty(forceNetwork)) {
                requestCacheControl = "public, max-age=$cacheMaxAge"
            }
            return if (TextUtils.isEmpty(requestCacheControl)) {
                originResponse
            } else {
                originResponse.newBuilder()
                    .header("Cache-Control", requestCacheControl)
                    .removeHeader("Pragma")
                    .build()
            }
        }
    }

}