package com.smilegateblog.smliegateblog.domain.usecase.scrap

import android.util.Log
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.scrap.PostScrapResponse
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.domain.repository.PostRepository
import com.smilegateblog.smliegateblog.domain.repository.ScrapRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostScrapUseCase @Inject constructor(private val scrapRepository: ScrapRepository){
    suspend fun invoke(postid: Int) : Flow<Resource<PostScrapResponse>> {
        Log.d("Post scrap", "usecase exec")

        return scrapRepository.postScrap(postid = postid)
    }
}