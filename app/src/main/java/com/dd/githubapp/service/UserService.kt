package com.dd.githubapp.service

import com.dd.githubapp.entity.User
import com.dd.githubapp.service.core.retrofit
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
interface UserApi {

    @GET("/user")
    fun getAuthenticatedUser(): Observable<User>
}

object UserService : UserApi by retrofit.create(UserApi::class.java)