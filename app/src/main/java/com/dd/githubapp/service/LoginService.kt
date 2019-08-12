package com.dd.githubapp.service

import com.dd.githubapp.model.User
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
interface LoginService {

    @GET("/user")
    fun login(): Observable<User>
}