package com.smilegateblog.smliegateblog.domain.usecase.post

import android.util.Log
import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponseItem
import com.smilegateblog.smliegateblog.domain.model.Post
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.domain.repository.PostRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentPostUseCase @Inject constructor(private val postRepository: PostRepository){
    operator fun invoke() : Flow<PagingData<Post>> {
        Log.d("Get recent post", "usecase exec")

        return postRepository.getRecentPost()
    }
}