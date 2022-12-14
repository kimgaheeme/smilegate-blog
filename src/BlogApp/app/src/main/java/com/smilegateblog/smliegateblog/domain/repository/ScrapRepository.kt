package com.smilegateblog.smliegateblog.domain.repository

import com.smilegateblog.smliegateblog.data.dto.post.PostPostRequest
import com.smilegateblog.smliegateblog.data.dto.post.PostPostResponse
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPost
import com.smilegateblog.smliegateblog.data.dto.scrap.PostScrapResponse
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow

interface ScrapRepository {
    suspend fun postScrap(userId: Int, postid: Int) : Flow<Resource<PostScrapResponse>>
    suspend fun delScrap(userId: Int, postid: Int) : Flow<Resource<Void>>
    suspend fun getScrapPost(userid: Int) : Flow<Resource<GetScrapPost>>
}