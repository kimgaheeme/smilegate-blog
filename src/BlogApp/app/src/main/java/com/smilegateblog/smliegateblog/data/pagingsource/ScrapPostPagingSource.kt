package com.smilegateblog.smliegateblog.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import com.smilegateblog.smliegateblog.data.api.ScrapApi
import com.smilegateblog.smliegateblog.data.dto.post.GetMyPostResponseItem
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPost
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPostItem
import com.smilegateblog.smliegateblog.domain.model.Post
import com.smilegateblog.smliegateblog.domain.model.toDomain

class ScrapPostPagingSource (
    private val scrapApi: ScrapApi,
    val userid: Int
): PagingSource<Int, Post>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Post> {
        return try {
            Log.d("ScrapPost", "paging source")
            val nextPageNumber = params.key ?: 1
            val response = scrapApi.getScrapPost(page = nextPageNumber, userid = userid)
            LoadResult.Page(
                data = response.body()!!.map { it.toDomain() },
                prevKey = null, // 이전 페이지는 불러오지 않음
                nextKey = if(response.body()!!.isEmpty()) null else nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            Log.d("ScrapPost", "paging 오류" + e.stackTraceToString())
            LoadResult.Error(e)

        }
    }
}