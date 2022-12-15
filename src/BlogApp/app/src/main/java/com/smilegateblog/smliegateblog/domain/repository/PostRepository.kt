package com.smilegateblog.smliegateblog.domain.repository

import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.post.*
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun postPost(postPostRequest: PostPostRequest) : Flow<Resource<PostPostResponse>>
    suspend fun getPost(postid: Int) : Flow<Resource<GetPostResponse>>
    suspend fun putPost(postPostRequest: PutPostRequest, postid: Int) : Flow<Resource<PutPostResponse>>
    suspend fun delPost(postid: Int) : Flow<Resource<Void>>
    fun getRecentPost() : Flow<PagingData<GetRecentPostResponseItem>>
    suspend fun getMostViewedPost() : Flow<Resource<GetMostViewedResponse>>
    suspend fun getMyPost() : Flow<PagingData<GetMyPostResponseItem>>
}