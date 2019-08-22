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
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        return chain.proceed(origin.newBuilder()
            .apply {
                when {
                    origin.url.pathSegments.contains("authorizations") -> {
                        val userCredentials = AccountManager.username + ":" + AccountManager.password
                        val auth =
                            "Basic " + String(Base64.encode(userCredentials.toByteArray(), Base64.DEFAULT)).trim()
                        header("Authorization", auth)
                    }
                    AccountManager.isLoggedin() -> {
                        val auth = "Token " + AccountManager.token
                        header("Authorization", auth)
                    }
                    else -> removeHeader("Authorization")
                }
            }
            .build())
    }

}