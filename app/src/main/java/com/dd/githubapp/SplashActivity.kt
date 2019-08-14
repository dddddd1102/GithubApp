package com.dd.githubapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.dd.githubapp.model.db.UserDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashActivity : BasicActivity() {

    companion object {
        private val TAG = SplashActivity::class.java.simpleName
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // TODO 判断是否已经登录，若未登录直接进入登录界面，若已经登录则进入主页
        UserDatabase.getInstance(this).userDao().findLoginUser().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "user: $it")

            }, {
                Log.d(TAG, "error: ${it.localizedMessage}");
            }, {
                Log.d(TAG, "complete!")
            })
    }
}
