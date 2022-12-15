package com.smilegateblog.smliegateblog.domain.usecase.comment

import android.util.Log
import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.dto.comment.PutCommentRequest
import com.smilegateblog.smliegateblog.data.dto.comment.PutCommentResponse
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPostItem
import com.smilegateblog.smliegateblog.domain.repository.CommentRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PutCommentUseCase @Inject constructor(private val commentRepository: CommentRepository){
    suspend fun invoke(commentid: Int, putCommentRequest: PutCommentRequest) : Flow<Resource<PutCommentResponse>> {
        Log.d("put comment", "usecase exec")

        return commentRepository.putComment(commentid = commentid, putCommentRequest = putCommentRequest)
    }
}