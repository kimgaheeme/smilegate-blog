package com.smilegateblog.smilegateteamprojecttest.domain.model

import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Message
import com.smilegateblog.smilegateteamprojecttest.model.MessageType
import java.util.*

data class Message(
    val messageId: String,
    val messageFromId: String,
    val chatroomId: String,
    var content: String,
    var createdAt: Date,
    var type: MessageType
)

fun Message.toMessage() = com.smilegateblog.smilegateteamprojecttest.domain.model.Message(
    messageId = this.messageId,
    messageFromId = this.messageFromID,
    chatroomId = this.chatroomId,
    content = this.content,
    createdAt = this.createdAt,
    type = when(this.type) {
        1 -> MessageType.TEXT
        2 -> MessageType.IMAGE
        3 -> MessageType.VIDEO
        else -> MessageType.ENTER
    }
)