package com.dd.githubapp.utils

import com.google.gson.Gson

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)