package com.dd.githubapp

import android.app.Application
import android.content.ContextWrapper

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
private lateinit var INSTANCE: Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}

object AppContext : ContextWrapper(INSTANCE)