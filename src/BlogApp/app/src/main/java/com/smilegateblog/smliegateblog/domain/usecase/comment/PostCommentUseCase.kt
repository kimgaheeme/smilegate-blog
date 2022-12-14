package com.smilegateblog.smliegateblog.domain.usecase.comment

import android.util.Log
import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.dto.comment.PostCommentRequest
import com.smilegateblog.smliegateblog.data.dto.comment.PostCommentResponse
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPostItem
import com.smilegateblog.smliegateblog.domain.repository.CommentRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostCommentUseCase @Inject constructor(private val commentRepository: CommentRepository){
    suspend operator fun invoke(postid: Int, postCommentRequest: PostCommentRequest) : Flow<Resource<PostCommentResponse>> {
        Log.d("post comment", "usecase exec")

        return commentRepository.postComment(postid = postid, postCommentRequest = postCommentRequest)
    }
}