package com.smilegateblog.smilegateteamprojecttest.model

data class People(
    var nickname: String,
    val friendId: String,
    val email: String,
    val profileImage: String,
    var block: Int?
)