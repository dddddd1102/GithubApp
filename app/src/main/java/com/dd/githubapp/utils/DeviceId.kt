package com.dd.githubapp.utils

import android.content.Context
import android.provider.Settings

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
val Context.deviceId: String
    get() = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)