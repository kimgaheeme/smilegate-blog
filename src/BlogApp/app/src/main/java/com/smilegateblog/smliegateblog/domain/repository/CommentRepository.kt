package com.smilegateblog.smliegateblog.domain.repository

import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.dto.comment.*
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    suspend fun getComments(postid: Int) : Flow<PagingData<GetCommentsResponseItem>>
    suspend fun postComment(postid: Int, postCommentRequest: PostCommentRequest) : Flow<Resource<PostCommentResponse>>
    suspend fun putComment(commentid: Int, putCommentRequest: PutCommentRequest) : Flow<Resource<PutCommentResponse>>
    suspend fun delComment(commentid: Int) : Flow<Resource<Void>>
}