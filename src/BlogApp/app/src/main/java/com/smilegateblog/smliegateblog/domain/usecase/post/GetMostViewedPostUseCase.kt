package com.smilegateblog.smliegateblog.domain.usecase.post

import android.util.Log
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.post.GetMostViewedResponse
import com.smilegateblog.smliegateblog.domain.model.Post
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.domain.repository.PostRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMostViewedPostUseCase @Inject constructor(private val postRepository: PostRepository){
    suspend operator fun invoke() :  Flow<Resource<List<Post>>> {
        Log.d("get most viewed post", "usecase exec")

        return postRepository.getMostViewedPost()
    }
}