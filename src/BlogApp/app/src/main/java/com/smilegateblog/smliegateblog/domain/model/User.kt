package com.smilegateblog.smliegateblog.domain.model

import com.smilegateblog.smliegateblog.data.dto.login.LoginResponse

data class User(
    var email: String = "",
    var nickname: String = "",
    var userId: String = ""
)

fun LoginResponse.toDomain(): User = User(
    email = this.email,
    nickname = this.nickname,
    userId = this.userId
)
