package com.dd.githubapp.model

import com.dd.githubapp.entity.AuthorizationReq
import com.dd.githubapp.entity.AuthorizationRsp
import com.dd.githubapp.entity.User
import com.dd.githubapp.service.AuthService
import com.dd.githubapp.service.UserService
import com.dd.githubapp.utils.fromJson
import com.dd.githubapp.utils.pref
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.lang.Exception

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
object AccountManager {
    var authId by pref(-1)
    var username by pref("")
    var password by pref("")
    var token by pref("")

    private var userJson by pref("")

    var currentUser: User? = null
        get() {
            if (field == null && userJson.isNotEmpty()) {
                field = Gson().fromJson<User>(userJson)
            }
            return field
        }
        set(value) {
            if (value == null) {
                userJson = ""
            } else {
                userJson = Gson().toJson(value)
            }
            field = value
        }

    fun isLoggedin(): Boolean = token.isNotEmpty()

    fun login() = AuthService.createAuthorization(AuthorizationReq())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnNext {
            if (it.token.isEmpty()) throw AccountException(it)
        }
        .retryWhen {
            it.flatMap {
                if (it is AccountException) {
                    AuthService.deleteAuthorization(it.authorizationRsp.id)
                } else {
                    Observable.error(it)
                }
            }
        }
        .flatMap {
            token = it.token
            authId = it.id
            UserService.getAuthenticatedUser()
        }
        .map {
            currentUser = it
        }

    fun logout() = AuthService.deleteAuthorization(authId)
        .doOnNext {
            if (it.isSuccessful) {
                authId = -1
                token = ""
                currentUser = null
            } else {
                throw HttpException(it)
            }
        }

    class AccountException(val authorizationRsp: AuthorizationRsp) : Exception("Already logged in.")

}