package com.smilegateblog.smliegateblog.domain.usecase.comment

import android.util.Log
import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.dto.comment.GetCommentsResponseItem
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPostItem
import com.smilegateblog.smliegateblog.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(private val commentRepository: CommentRepository){
    suspend fun invoke(postid: Int) : Flow<PagingData<GetCommentsResponseItem>> {
        Log.d("get comment ", "usecase exec")

        return commentRepository.getComments(postid = postid)
    }
}