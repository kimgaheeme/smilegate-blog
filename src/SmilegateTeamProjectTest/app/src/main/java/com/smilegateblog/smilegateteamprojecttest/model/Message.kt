package com.smilegateblog.smilegateteamprojecttest.model

import java.time.format.DateTimeFormatter

data class Message(
    val messageId: String,
    val authorId: Long,
    val authorName: String,
    val content: String,
    val createdAt: DateTimeFormatter,
    val type: MessageType,
    val profileImage: Int
)

enum class MessageType {
    IMAGE,
    TEXT,
    VIDEO
}