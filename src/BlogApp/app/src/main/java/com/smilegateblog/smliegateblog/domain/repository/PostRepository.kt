package com.smilegateblog.smliegateblog.domain.repository

import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.post.*
import com.smilegateblog.smliegateblog.domain.model.Post
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {
    suspend fun postPost(postPostRequest: PostPostRequest, file: File) : Flow<Resource<PostPostResponse>>
    suspend fun getPost(postid: Int) : Flow<Resource<GetPostResponse>>
    suspend fun putPost(postPostRequest: PutPostRequest, postid: Int, file: File) : Flow<Resource<PutPostResponse>>
    suspend fun delPost(postid: Int) : Flow<Resource<Boolean>>
    fun getRecentPost() : Flow<PagingData<Post>>
    suspend fun getMostViewedPost() : Flow<Resource<List<Post>>>
    suspend fun getMyPost() : Flow<PagingData<Post>>
}