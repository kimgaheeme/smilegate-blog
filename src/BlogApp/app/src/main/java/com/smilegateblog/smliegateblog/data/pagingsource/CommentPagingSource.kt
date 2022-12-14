package com.smilegateblog.smliegateblog.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import com.smilegateblog.smliegateblog.data.api.CommentApi
import com.smilegateblog.smliegateblog.data.api.PostApi
import com.smilegateblog.smliegateblog.data.dto.comment.GetCommentsResponse
import com.smilegateblog.smliegateblog.data.dto.comment.GetCommentsResponseItem
import com.smilegateblog.smliegateblog.data.dto.post.GetMyPostResponseItem
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponse
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponseItem

class CommentPagingSource (
    private val commentApi: CommentApi,
    val postid: Int
): PagingSource<Int, GetCommentsResponseItem>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, GetCommentsResponseItem> {
        return try {

            val nextPageNumber = params.key ?: 1
            val response = commentApi.getComments(page = nextPageNumber, postid = postid)
            LoadResult.Page(
                data = response.body()!!,
                prevKey = null, // 이전 페이지는 불러오지 않음
                nextKey = nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            Log.d("GetComment", "paging 오류" + e.stackTraceToString())
            LoadResult.Error(e)

        }
    }
}