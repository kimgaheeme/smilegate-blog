package com.smilegateblog.smliegateblog.data.api

import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.login.LoginResponse
import com.smilegateblog.smliegateblog.data.dto.login.MyInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {

    //로그인 API 생성후 수정할 것
    @POST("/users")
    suspend fun postLogin(
        @Body loginRequest: LoginRequest
    ) : Response<LoginResponse>

    //GET으로 바꿀 것
    @POST("/users/myinfo")
    suspend fun getMyInfo(
        @Query("userId") userId: Int
    ) : Response<MyInfoResponse>

//    @POST("/users")
//    suspend fun postLogin(
//        @Body loginRequest: LoginRequest
//    ) : Response<LoginResponse>
}