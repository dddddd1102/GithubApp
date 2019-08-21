package com.dd.githubapp.mvp

import android.content.res.Configuration
import android.os.Bundle

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
interface ILifeCycle {

    fun onCreate(savedInstanceState: Bundle?)

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()

    fun onSaveInstanceState(outState: Bundle)

    fun onViewStateRestored(savedInstanceState: Bundle?)

    fun onConfigurationChanged(newConfig: Configuration)

}