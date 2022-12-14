package com.smilegateblog.smliegateblog.data.api

import com.smilegateblog.smliegateblog.data.dto.post.PostPostRequest
import com.smilegateblog.smliegateblog.data.dto.post.PostPostResponse
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPost
import com.smilegateblog.smliegateblog.data.dto.scrap.PostScrapResponse
import retrofit2.Response
import retrofit2.http.*

interface ScrapApi {

    @POST("/posts/scrap/{postid}")
    suspend fun postScrap(
        @Query("userId") userId: Int,
        @Path("postid") postid: Int
    ) : Response<PostScrapResponse>

    @DELETE("/posts/scrap/{postid}")
    suspend fun delScrap(
        @Query("userId") userId: Int,
        @Path("postid") postid: Int
    ) : Response<Void>

    @GET("/posts/scrap/my")
    suspend fun getScrapPost(
        @Query("userid") userid: Int,
        @Query("page") page: Int,
    ) : Response<GetScrapPost>
}