package com.dd.githubapp.common.ext

import android.util.Log
import java.io.File

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
fun File.ensureDir(): Boolean {
    try {
        isDirectory.no {
            isFile.yes {
                delete()
            }
            return mkdirs()
        }
    } catch (e: Exception) {
        Log.w("FileExt", e.message)
    }
    return false
}