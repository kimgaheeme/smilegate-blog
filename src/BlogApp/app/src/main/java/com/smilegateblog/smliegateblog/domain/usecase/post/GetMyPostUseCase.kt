package com.smilegateblog.smliegateblog.domain.usecase.post

import android.util.Log
import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.post.GetMyPostResponseItem
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.domain.repository.PostRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyPostUseCase @Inject constructor(private val postRepository: PostRepository){
    suspend fun invoke(userId: Int) : Flow<PagingData<GetMyPostResponseItem>> {
        Log.d("Get my post", "usecase exec")

        return postRepository.getMyPost()
    }
}