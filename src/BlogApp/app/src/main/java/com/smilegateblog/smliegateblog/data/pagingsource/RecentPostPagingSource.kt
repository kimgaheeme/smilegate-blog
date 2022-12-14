package com.smilegateblog.smliegateblog.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import com.smilegateblog.smliegateblog.data.api.PostApi
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponse
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponseItem

class RecentPostPagingSource (
    private val postApi: PostApi
): PagingSource<Int, GetRecentPostResponseItem>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, GetRecentPostResponseItem> {
        return try {

            val nextPageNumber = params.key ?: 1
            val response = postApi.getRecentPost(nextPageNumber)
            LoadResult.Page(
                data = response.body()!!,
                prevKey = null, // 이전 페이지는 불러오지 않음
                nextKey = nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            Log.d("RecentPost", "paging 오류" + e.stackTraceToString())
            LoadResult.Error(e)

        }
    }
}