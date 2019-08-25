package com.dd.githubapp.utils

import android.view.View
import androidx.core.view.ViewCompat
import com.dd.githubapp.common.ext.otherwise
import com.dd.githubapp.common.ext.yes
import com.google.android.material.navigation.NavigationView

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
inline fun NavigationView.doOnLayoutAvailable(crossinline block: () -> Unit) {
    ViewCompat.isLaidOut(this).yes {
        block()
    }.otherwise {
        addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                removeOnLayoutChangeListener(this)
                block()
            }
        })
    }
}