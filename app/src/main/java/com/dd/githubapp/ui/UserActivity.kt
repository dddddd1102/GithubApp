package com.dd.githubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dd.githubapp.BuildConfig
import com.dd.githubapp.R
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        initView()
    }

    private fun initView() {
        tv_user.text = "DAI DONG"
        tv_nickname.text = "daidong"
        BuildConfig.APPLICATION_ID
    }
}
