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

interface OnAccountStateChangeListener {

    fun onLogin(user: User)

    fun onLogout()
}

object AccountManager {
    var authId by pref(-1)
    var username by pref("")
    var password by pref("")
    var token by pref("")

    private var userJson by pref("")

    private var currentUser: User? = null
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

    val onAccountStateChangeListeners = ArrayList<OnAccountStateChangeListener>()

    private fun notifyLogin(user: User) {
        onAccountStateChangeListeners.forEach {
            it.onLogin(user)
        }
    }

    private fun notifyLogout() {
        onAccountStateChangeListeners.forEach {
            it.onLogout()
        }
    }

    fun isLogin(): Boolean = token.isNotEmpty()

    fun login(): Observable<Unit> = AuthService.createAuthorization(AuthorizationReq())
        .doOnNext {
            if (it.token.isEmpty()) throw AccountException(it)
        }
        .retryWhen { it ->
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
            notifyLogin(it)
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun logout() = AuthService.deleteAuthorization(authId)
        .doOnNext {
            if (it.isSuccessful) {
                authId = -1
                token = ""
                currentUser = null
                notifyLogout()
            } else {
                throw HttpException(it)
            }
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    class AccountException(val authorizationRsp: AuthorizationRsp) : Exception("Already logged in.")

}