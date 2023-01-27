package com.smilegateblog.smilegateteamprojecttest.domain.model

data class People(
    val peopleId: String,
    var nickname: String,
    var profileImg: String,
    val email: String,
    var status: Int = 1
)

enum class PeopleStatusType {
    FRIEND, BLOCK, REQUEST, NONE
}