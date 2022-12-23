package com.smilegateblog.smliegateblog.data.pref.model

import com.smilegateblog.smliegateblog.data.dto.login.LoginResponse
import com.smilegateblog.smliegateblog.domain.model.User


data class UserPref(
    val userId: Int,
    val email: String,
    val nickname: String
) {
    override fun toString(): String {
        return "userId : $userId, email : $email, nickname : $nickname"
    }
}

fun UserPref.toState(): User =
    User(userId = userId, email = email, nickname = nickname)


fun LoginResponse.toPref(): UserPref = UserPref(
    userId = this.userId,
    email = this.email,
    nickname = this.nickname
)