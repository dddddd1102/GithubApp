package com.dd.githubapp.setting

import com.dd.githubapp.AppContext
import com.dd.githubapp.utils.deviceId
import okhttp3.internal.http2.Http2Reader.Companion.logger

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
object Configs {
    object Account {
        val SCOPES = listOf("user", "repo", "notifications", "gist", "admin:org")
        const val clientId = "53a200dd3d8723a1e8fc"
        const val clientSecret = "7f83c3889f835bd532e90470bdeb537581bd39ef"
        const val note = "GHKotlinApp"
        const val noteUrl = "https://github.com/dddddd1102"

        val fingerPrint by lazy {
            (AppContext.deviceId + clientId).also { logger.info("fingerPrint: $it") }
        }
    }
}