package com.dd.githubapp.service.interceptor

import android.util.Base64
import android.util.Log
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
                        Log.d("OkHttp", "userInfo=>$userCredentials")
                        val auth =
                            "Basic " + Base64.encodeToString(userCredentials.toByteArray(), Base64.NO_WRAP)
                        header("Authorization", auth)
                    }
                    AccountManager.isLogin() -> {
                        val auth = "Token " + AccountManager.token
                        header("Authorization", auth)
                    }
                    else -> removeHeader("Authorization")
                }
            }
            .build())
    }

}