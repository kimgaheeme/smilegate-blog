package com.smilegateblog.smliegateblog.domain.usecase

import com.smilegateblog.smliegateblog.domain.usecase.login.CheckLoginUseCase
import com.smilegateblog.smliegateblog.domain.usecase.login.GetMyInfoUseCase
import javax.inject.Inject

data class LoginUseCase @Inject constructor(
    val checkLoginUseCase: CheckLoginUseCase,
    val getMyInfoUseCase: GetMyInfoUseCase,
    val loginUseCase: com.smilegateblog.smliegateblog.domain.usecase.login.LoginUseCase
)
