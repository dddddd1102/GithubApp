package com.dd.githubapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.dd.githubapp.common.ext.otherwise
import com.dd.githubapp.common.ext.yes
import com.dd.githubapp.model.AccountManager

class SplashActivity : BasicActivity() {


    companion object {
        private val TAG = SplashActivity::class.java.simpleName

        private const val DELAY_TIME: Long = 1000

        private const val MSG_LOGIN = 10001

        private const val MSG_HOME = 10002
    }

    private val handler = Handler(Handler.Callback {
        if (it.what == MSG_LOGIN) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        if (it.what == MSG_HOME) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return@Callback false
    })

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AccountManager.isLogin().yes {
            Log.d("SplashActivity", "isLogin=true")
            handler.sendEmptyMessageDelayed(MSG_HOME, DELAY_TIME)
        }.otherwise {
            Log.d("SplashActivity", "isLogin=false")
            handler.sendEmptyMessageDelayed(MSG_LOGIN, DELAY_TIME)
        }
    }
}
