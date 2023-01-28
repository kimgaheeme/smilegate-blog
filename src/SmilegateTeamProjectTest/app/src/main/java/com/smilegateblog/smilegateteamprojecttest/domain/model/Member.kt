package com.smilegateblog.smilegateteamprojecttest.domain.model

import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Member

data class Member(
    val memberId: String,
    var profileImg: String,
    var nickname: String,
    var readMessage: String?
)

fun Member.toMember() = com.smilegateblog.smilegateteamprojecttest.domain.model.Member(
    memberId = this.memberId,
    profileImg = this.profileImg,
    nickname = this.nickname,
    readMessage = this.readMessage
)