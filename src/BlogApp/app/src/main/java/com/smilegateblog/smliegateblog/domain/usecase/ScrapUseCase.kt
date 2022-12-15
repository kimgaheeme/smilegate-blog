package com.smilegateblog.smliegateblog.domain.usecase

import com.smilegateblog.smliegateblog.domain.usecase.scrap.DelScrapUseCase
import com.smilegateblog.smliegateblog.domain.usecase.scrap.GetScrapPostUseCase
import com.smilegateblog.smliegateblog.domain.usecase.scrap.PostScrapUseCase
import javax.inject.Inject

data class ScrapUseCase @Inject constructor(
    val delScrapUseCase: DelScrapUseCase,
    val getScrapPostUseCase: GetScrapPostUseCase,
    val postScrapUseCase: PostScrapUseCase
)
