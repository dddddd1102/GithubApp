package com.dd.githubapp.service.core

import android.text.TextUtils
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

    val baseUrl = "https://api.github.com/"

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