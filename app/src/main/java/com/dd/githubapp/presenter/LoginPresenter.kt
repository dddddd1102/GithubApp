package com.dd.githubapp.presenter

import com.dd.githubapp.BuildConfig
import com.dd.githubapp.ui.LoginActivity
import com.dd.githubapp.model.AccountManager
import com.dd.githubapp.mvp.impl.BasePresenter

/**
 * GithubApp
 *
 * @author    daidong
 *
 */

class LoginPresenter : BasePresenter<LoginActivity>() {

    fun doLogin(name: String, password: String) {
        AccountManager.username = name
        AccountManager.password = password
        view.onLoginStart()
        AccountManager.login()
            .subscribe({
                view.onLoginSuccess()
            }, {
                view.onLoginError(it)
            })

    }

    fun checkUsername(name: String): Boolean {
        return true
    }

    fun checkPassword(password: String): Boolean {
        return true
    }

    override fun onResume() {
        super.onResume()
        if (BuildConfig.DEBUG) {
            view.onDataInit(BuildConfig.testUsername, BuildConfig.testPassword)
        } else {
            view.onDataInit(AccountManager.username, AccountManager.password)
        }
    }
}

