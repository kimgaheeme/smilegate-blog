package com.smilegateblog.smliegateblog.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import com.smilegateblog.smliegateblog.data.api.PostApi
import com.smilegateblog.smliegateblog.data.dto.post.GetMyPostResponseItem
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponse
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponseItem
import com.smilegateblog.smliegateblog.domain.model.Post
import com.smilegateblog.smliegateblog.domain.model.toDomain

class MyPostPagingSource (
    private val postApi: PostApi,
    val userId: Int
): PagingSource<Int, Post>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Post> {
        return try {

            val nextPageNumber = params.key ?: 1
            val response = postApi.getMyPost(nextPageNumber, userId)
            LoadResult.Page(
                data = response.body()!!.map { it.toDomain() },
                prevKey = null, // 이전 페이지는 불러오지 않음
                nextKey = if(response.body()!!.isEmpty()) null else nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            Log.d("MyPost", "paging 오류" + e.stackTraceToString())
            LoadResult.Error(e)

        }
    }
}