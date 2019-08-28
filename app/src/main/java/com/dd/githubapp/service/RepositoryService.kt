package com.dd.githubapp.service

import com.dd.githubapp.entity.Repository
import com.dd.githubapp.entity.SearchRepositories
import com.dd.githubapp.service.core.retrofit
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * GithubApp
 *
 * @author    daidong
 *
 */

// TODO 返回值处理
interface RepositoryApi {


    @GET("/users/{owner}/repos?type=all")
    fun listRepositoriesOfUser(@Path("owner") owner: String, @Query("page") page: Int = 1, @Query("per_page") per_page: Int = 10): Any

    @GET("/search/repositories?order=desc&sort=updated")
    fun searchRepositories(@Query("page") page: Int = 1, @Query("q") q: String, @Query("per_page") per_page: Int = 10): Observable<SearchRepositories>

    @GET("repos/{owner}/{repo}")
    fun getRepository(@Path("owner") owner: String, @Path("repo") repo: String): Observable<Repository>

}

object RepositoryService : RepositoryApi by retrofit.create(RepositoryApi::class.java)