package com.dd.githubapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler

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

        }
        return@Callback false
    })

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // TODO 判断是否已经登录，若未登录直接进入登录界面，若已经登录则进入主页
//        UserDatabase.getInstance(this).userDao().findLoginUser().subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.d(TAG, "user: $it")
//
//            }, {
//                Log.d(TAG, "error: ${it.localizedMessage}");
//            }, {
//                Log.d(TAG, "complete!")
//            })
        handler.sendEmptyMessageDelayed(MSG_LOGIN, DELAY_TIME)

    }
}
