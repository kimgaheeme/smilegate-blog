package com.smilegateblog.smliegateblog.domain.repository

import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.dto.post.PostPostRequest
import com.smilegateblog.smliegateblog.data.dto.post.PostPostResponse
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPost
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPostItem
import com.smilegateblog.smliegateblog.data.dto.scrap.PostScrapResponse
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow

interface ScrapRepository {
    suspend fun postScrap(postid: Int) : Flow<Resource<PostScrapResponse>>
    suspend fun delScrap(postid: Int) : Flow<Resource<Void>>
    fun getScrapPost() : Flow<PagingData<GetScrapPostItem>>
}