package com.dd.githubapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.dd.githubapp.R
import com.dd.githubapp.common.ext.otherwise
import com.dd.githubapp.common.ext.yes
import com.dd.githubapp.mvp.impl.BaseActivity
import com.dd.githubapp.presenter.LoginPresenter
import com.qmuiteam.qmui.util.QMUIKeyboardHelper
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity<LoginPresenter>() {


    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        QMUIStatusBarHelper.translucent(this)
        setContentView(R.layout.activity_login)

        btn_login.onClick {
            val username = el_username.editText
            val password = el_password.editText
            presenter.checkUsername(username)
                .yes {
                    presenter.checkPassword(password)
                        .yes {
                            QMUIKeyboardHelper.hideKeyboard(btn_login)
                            presenter.doLogin(username, password)
                        }
                        .otherwise {
                            toast(R.string.login_require_password)
                        }
                }
                .otherwise {
                    toast(R.string.login_require_username)
                }
        }
    }

    fun onLoginStart() {

    }

    fun onLoginError(e: Throwable) {
        toast("登录失败: ${e.localizedMessage}")
    }

    fun onLoginSuccess() {
        toast("登录成功")
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onDataInit(name: String, password: String) {
        el_username.setText(name)
        el_password.setText(password)
    }
}
