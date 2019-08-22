package com.dd.githubapp.utils

import com.dd.githubapp.AppContext
import com.dd.githubapp.common.sharedpreferences.Preference
import kotlin.reflect.jvm.jvmName

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
inline fun <reified R, T> R.pref(default: T) = Preference(AppContext, "", default, R::class.jvmName)