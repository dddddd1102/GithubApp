package com.dd.githubapp.service

import com.dd.githubapp.entity.AuthorizationReq
import com.dd.githubapp.entity.AuthorizationRsp
import com.dd.githubapp.service.core.retrofit
import com.dd.githubapp.setting.Configs
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
interface AuthApi {

    @PUT("/authorizations/clients/${Configs.Account.clientId}/{fingerPrint}")
    fun createAuthorization(@Body req: AuthorizationReq, @Path("fingerPrint") fingerPrint: String = Configs.Account.fingerPrint)
            : Observable<AuthorizationRsp>


    @DELETE("/authorizations/{id}")
    fun deleteAuthorization(@Path("id") id: Int): Observable<Response<Any>>

}

object AuthService : AuthApi by retrofit.create(AuthApi::class.java)