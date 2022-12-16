package com.smilegateblog.smliegateblog.domain.usecase.post

import android.util.Log
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.domain.repository.PostRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DelPostUseCase @Inject constructor(private val postRepository: PostRepository){
    suspend operator fun invoke(postid: Int) : Flow<Resource<Void>> {
        Log.d("DelPost", "usecase exec")

        return postRepository.delPost(postid)
    }
}