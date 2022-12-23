package com.smilegateblog.smliegateblog.domain.model

import com.smilegateblog.smliegateblog.data.dto.login.LoginResponse
import com.smilegateblog.smliegateblog.data.dto.login.MyInfoResponse

data class User(
    var email: String = "",
    var nickname: String = "",
    var userId: Int = 0
)

fun LoginResponse.toDomain(): User = User(
    email = this.email,
    nickname = this.nickname,
    userId = this.userId
)


fun MyInfoResponse.toDomain(): User = User(
    email = this.email,
    nickname = this.nickname
)