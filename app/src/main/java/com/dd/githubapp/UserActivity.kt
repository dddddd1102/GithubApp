package com.dd.githubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        initView()
    }

    private fun initView() {
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        QMUIStatusBarHelper.translucent(this)
        tv_user.text = "DAI DONG"
        tv_nickname.text = "daidong"
        BuildConfig.APPLICATION_ID
    }
}
