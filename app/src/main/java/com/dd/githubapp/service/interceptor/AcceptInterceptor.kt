package com.dd.githubapp.service.interceptor

import android.util.Base64
import com.dd.githubapp.model.AccountManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
class AcceptInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        return chain.proceed(origin.newBuilder()
            .apply {
                header("accept", "application/vnd.github.v3.full+json, ${origin.header("accept") ?: ""}")
            }
            .build())
    }

}