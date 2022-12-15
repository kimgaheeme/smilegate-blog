package com.smilegateblog.smliegateblog.domain.usecase

import com.smilegateblog.smliegateblog.domain.usecase.comment.DelCommentUseCase
import com.smilegateblog.smliegateblog.domain.usecase.comment.GetCommentsUseCase
import com.smilegateblog.smliegateblog.domain.usecase.comment.PostCommentUseCase
import com.smilegateblog.smliegateblog.domain.usecase.comment.PutCommentUseCase
import com.smilegateblog.smliegateblog.domain.usecase.login.CheckLoginUseCase
import com.smilegateblog.smliegateblog.domain.usecase.login.GetMyInfoUseCase
import javax.inject.Inject

data class CommentUseCase @Inject constructor(
    val delCommentUseCase: DelCommentUseCase,
    val getCommentsUseCase: GetCommentsUseCase,
    val postCommentUseCase: PostCommentUseCase,
    val putCommentUseCase: PutCommentUseCase
)
