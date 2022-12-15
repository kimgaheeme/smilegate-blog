package com.smilegateblog.smliegateblog.domain.usecase.post

import android.util.Log
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.post.GetPostResponse
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.domain.repository.PostRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostUseCase @Inject constructor(private val postRepository: PostRepository){
    suspend fun invoke(postid: Int, userid: Int) : Flow<Resource<GetPostResponse>> {
        Log.d("Get post", "usecase exec")

        return postRepository.getPost(postid = postid)
    }
}