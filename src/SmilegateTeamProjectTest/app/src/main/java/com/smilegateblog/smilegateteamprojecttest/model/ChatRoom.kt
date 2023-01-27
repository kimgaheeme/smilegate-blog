package com.smilegateblog.smilegateteamprojecttest.model

import java.time.format.DateTimeFormatter
import java.util.*

data class ChatRoom(
    val chatRoomId: String,
    var title: String,
    var lastMessageContent: String,
    var lastMessageCreatedAt: DateTimeFormatter,
    var unread: Int,
    var type: Int,
    var images: List<Member>
)

