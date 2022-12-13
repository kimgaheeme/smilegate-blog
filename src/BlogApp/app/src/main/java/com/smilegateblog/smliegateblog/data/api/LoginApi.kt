package com.smilegateblog.smliegateblog.data.api

import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/users")
    suspend fun postLogin(
        @Body loginRequest: LoginRequest
    ) : Response<LoginResponse>
}