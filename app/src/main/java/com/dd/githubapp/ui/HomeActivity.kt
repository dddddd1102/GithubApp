package com.dd.githubapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dd.githubapp.R
import com.dd.githubapp.model.AccountManager
import com.dd.githubapp.utils.doOnLayoutAvailable
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.nav_header_home.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_user, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send, R.id.nav_about
            ), drawer_layout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)

        initNavigationView()
        initNavigationEvent()
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initNavigationView() {
        nav_view.doOnLayoutAvailable {
            AccountManager.currentUser?.let {
                Glide.with(this).load(it.avatarUrl).into(ivNavAvatar)
                tvNavUsername.text = it.name
                tvNavEmail.text = it.email ?: ""

            } ?: kotlin.run { }
        }
    }

    private fun initNavigationEvent() {
        nav_view.doOnLayoutAvailable {
            lltNavHeader.onClick {
                

            }
        }
    }

}
