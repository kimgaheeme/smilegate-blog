package com.smilegateblog.smliegateblog.domain.usecase.scrap

import android.util.Log
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.domain.repository.PostRepository
import com.smilegateblog.smliegateblog.domain.repository.ScrapRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DelScrapUseCase @Inject constructor(private val scrapRepository: ScrapRepository){
    suspend fun invoke(userId: Int, postid: Int) : Flow<Resource<Void>> {
        Log.d("Del scrap", "usecase exec")

        return scrapRepository.delScrap(userId = userId, postid = postid)
    }
}