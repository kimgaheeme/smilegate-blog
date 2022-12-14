package com.smilegateblog.smliegateblog.domain.repository

import com.smilegateblog.smliegateblog.data.dto.comment.GetCommentsResponse
import com.smilegateblog.smliegateblog.data.dto.comment.PostCommentResponse
import com.smilegateblog.smliegateblog.data.dto.comment.PutCommentResponse
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    suspend fun getComments(postid: Int) : Flow<Resource<GetCommentsResponse>>
    suspend fun postComment(userId: Int, postid: Int) : Flow<Resource<PostCommentResponse>>
    suspend fun putComment(commentid: Int) : Flow<Resource<PutCommentResponse>>
    suspend fun delComment(commentid: Int) : Flow<Resource<Void>>
}