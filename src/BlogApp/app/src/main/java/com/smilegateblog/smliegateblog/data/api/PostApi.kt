package com.smilegateblog.smliegateblog.data.api

import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.login.LoginResponse
import com.smilegateblog.smliegateblog.data.dto.post.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface PostApi {

    @Multipart
    @POST("/posts")
    suspend fun postPost(
        @Part image: MultipartBody.Part?,
        @Query("userId") userId: Int,
        @Query("title") title: String,
        @Query("content") content: String,
        @Query("postImage") postImage: String,
    ) : Response<PostPostResponse>

    @GET("/posts/{postid}")
    suspend fun getPost(
        @Path("postid") postid: Int,
        @Query("userid") userid: Int
    ) : Response<GetPostResponse>

    @Multipart
    @PUT("/posts/{postid}")
    suspend fun putPost(
        @Part image: MultipartBody.Part?,
        @Path("postid") postid: Int,
        @Query("title") title: String,
        @Query("content") content: String,
        @Query("postImage") postImage: String,
    ) : Response<PutPostResponse>

    @DELETE("/posts/{postid}")
    suspend fun delPost(
        @Path("postid") postid: Int
    ) : Response<Void>

    @GET("/posts/recent")
    suspend fun getRecentPost(
        @Query("page") page: Int
    ) : Response<GetRecentPostResponse>

    @GET("/posts/most-viewed")
    suspend fun getMostViewedPost(
    ) : Response<GetMostViewedResponse>

    @GET("/posts/myself")
    suspend fun getMyPost(
        @Query("page") page: Int,
        @Query("userId") userId: Int
    ) : Response<GetMyPostResponse>
}