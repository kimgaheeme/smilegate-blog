package com.smilegateblog.smliegateblog.domain.usecase.post

import android.util.Log
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.post.PostPostRequest
import com.smilegateblog.smliegateblog.data.dto.post.PostPostResponse
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.domain.repository.PostRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostPostUseCase @Inject constructor(private val postRepository: PostRepository){
    suspend fun invoke(postPostRequest: PostPostRequest, userId: Int) : Flow<Resource<PostPostResponse>> {
        Log.d("post post request", "usecase exec")

        return postRepository.postPost(postPostRequest = postPostRequest)
    }
}