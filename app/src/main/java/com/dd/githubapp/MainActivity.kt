package com.dd.githubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        QMUIStatusBarHelper.translucent(this)
        btn_test.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }
}
