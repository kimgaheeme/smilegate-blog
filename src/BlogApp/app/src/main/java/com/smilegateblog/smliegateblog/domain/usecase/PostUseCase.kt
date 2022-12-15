package com.smilegateblog.smliegateblog.domain.usecase

import com.smilegateblog.smliegateblog.domain.usecase.login.CheckLoginUseCase
import com.smilegateblog.smliegateblog.domain.usecase.login.GetMyInfoUseCase
import com.smilegateblog.smliegateblog.domain.usecase.post.*
import javax.inject.Inject

data class PostUseCase @Inject constructor(
    val delPostUseCase: DelPostUseCase,
    val getMostViewedPostUseCase: GetMostViewedPostUseCase,
    val getMyPostUseCase: GetMyPostUseCase,
    val getPostUseCase: DelPostUseCase,
    val getRecentPostUseCase: GetRecentPostUseCase,
    val postPostUseCase: PostPostUseCase,
    val putPostUseCase: PutPostUseCase
)
