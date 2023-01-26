package com.smilegateblog.smilegateteamprojecttest.model

import java.time.format.DateTimeFormatter

data class ChatRoom(
    val chatRoomId: String,
    var title: String,
    var lastMessageContent: String,
    var lastMessageCreatedAt: DateTimeFormatter,
    var unread: Int,
    var members: List<Member>
)
