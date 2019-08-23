package com.dd.githubapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.dd.githubapp.common.ext.otherwise
import com.dd.githubapp.common.ext.yes
import com.dd.githubapp.mvp.impl.BaseActivity
import com.dd.githubapp.presenter.LoginPresenter
import com.dd.githubapp.service.LoginService
import com.dd.githubapp.service.core.AppRetrofit
import com.qmuiteam.qmui.util.QMUIKeyboardHelper
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_user.*

class LoginActivity : BaseActivity<LoginPresenter>() {


    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        QMUIStatusBarHelper.translucent(this)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
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
                            Toast.makeText(this, R.string.login_require_password, Toast.LENGTH_SHORT).show()
                        }
                }
                .otherwise {
                    Toast.makeText(this, R.string.login_require_username, Toast.LENGTH_SHORT).show()
                }
        }
    }

//    private fun checkValue(username: String, password: String): Boolean {
//        if (TextUtils.isEmpty(username)) {
//            Toast.makeText(this, R.string.login_require_username, Toast.LENGTH_SHORT).show()
//            return false
//        }
//        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, R.string.login_require_password, Toast.LENGTH_SHORT).show()
//            return false
//        }
//        return true
//    }
//
//    @SuppressLint("CheckResult")
//    private fun login(username: String, password: String) {
//        AppRetrofit.createRetrofit(LoginService::class.java, username, password)
//            .login()
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                val dialog = QMUITipDialog.Builder(this)
//                    .setTipWord("登录成功!")
//                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
//                    .create()
//                dialog.show()
//                dismiss(dialog)
//                Log.d("LoginActivity", "login success: $it")
//                val intent = Intent(this, UserActivity::class.java)
//                startActivity(intent)
//            }, {
//                val dialog = QMUITipDialog.Builder(this)
//                    .setTipWord("登录失败: ${it.localizedMessage}")
//                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
//                    .create()
//                dialog.show()
//                dismiss(dialog)
//                Log.d("LoginActivity", "login error: ${it.localizedMessage}")
//            }, {
//                Log.d("LoginActivity", "login complete!")
//            })
//    }
//
//    private fun dismiss(dialog: QMUITipDialog) {
//        handler.postDelayed({
//            dialog.dismiss()
//        }, 3000)
//    }

    fun onLoginStart() {

    }

    fun onLoginError(e: Throwable) {
        Toast.makeText(this, "登录失败: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    fun onLoginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
    }

    fun onDataInit(name: String, password: String) {
        el_username.setText(name)
        el_password.setText(password)
    }
}
