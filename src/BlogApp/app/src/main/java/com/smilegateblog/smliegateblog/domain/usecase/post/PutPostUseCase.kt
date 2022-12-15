package com.smilegateblog.smliegateblog.domain.usecase.post

import android.util.Log
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.post.PutPostRequest
import com.smilegateblog.smliegateblog.data.dto.post.PutPostResponse
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.domain.repository.PostRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PutPostUseCase @Inject constructor(private val postRepository: PostRepository){
    suspend fun invoke(putPostRequest: PutPostRequest, postid: Int) : Flow<Resource<PutPostResponse>> {
        Log.d("put post use case", "usecase exec")

        return postRepository.putPost(postPostRequest = putPostRequest, postid = postid)
    }
}