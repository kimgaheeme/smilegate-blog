package com.smilegateblog.smliegateblog.domain.repository

import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.post.*
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun postPost(postPostRequest: PostPostRequest, userId: Int) : Flow<Resource<PostPostResponse>>
    suspend fun getPost(postid: Int, userid: Int) : Flow<Resource<GetPostResponse>>
    suspend fun putPost(postPostRequest: PutPostRequest, postid: Int) : Flow<Resource<PutPostResponse>>
    suspend fun delPost(postid: Int) : Flow<Resource<Void>>
    suspend fun getRecentPost() : Flow<Resource<GetRecentPostResponse>>
    suspend fun getMostViewedPost() : Flow<Resource<GetMostViewedResponse>>
    suspend fun getMyPost(userId: Int) : Flow<Resource<GetMyPostResponse>>
}