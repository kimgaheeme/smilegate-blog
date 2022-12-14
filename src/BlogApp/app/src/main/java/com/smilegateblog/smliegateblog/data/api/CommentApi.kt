package com.smilegateblog.smliegateblog.data.api

import com.smilegateblog.smliegateblog.data.dto.comment.*
import com.smilegateblog.smliegateblog.data.dto.post.PutPostRequest
import com.smilegateblog.smliegateblog.data.dto.scrap.PostScrapResponse
import retrofit2.Response
import retrofit2.http.*

interface CommentApi {

    @GET("/posts/{postid}/comments")
    suspend fun getComments(
        @Query("page") page: Int,
        @Path("postid") postid: Int
    ) : Response<GetCommentsResponse>

    @POST("/posts/{postid}/comments")
    suspend fun postComment(
        @Body postCommentRequest: PostCommentRequest,
        @Query("userId") userId: Int,
        @Path("postid") postid: Int
    ) : Response<PostCommentResponse>

    @PUT("/posts/comments/{commentid}")
    suspend fun putComment(
        @Body putCommentRequest: PutCommentRequest,
        @Path("commentid") commentid: Int
    ) : Response<PutCommentResponse>

    @DELETE("/posts/commments/{commentid}")
    suspend fun delComment(
        @Path("commentid") commentid: Int
    ) : Response<Void>
}