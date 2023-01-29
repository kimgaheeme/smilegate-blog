package com.smilegateblog.smilegateteamprojecttest.domain.model

import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.ChatRoomMemberImage
import java.util.*

data class ChatRoom(
    val chatroomId: String,
    var title: String,
    var unread: Int,
    var content: String,
    var createdAt: Date,
    var images: List<String>,
    val type: ChatRoomType
)

enum class ChatRoomType {
    ONE, MULTIPLE
}

fun ChatRoomMemberImage.toChatRoom(): ChatRoom = ChatRoom(
    chatroomId = this.chatroom.chatroomId,
    title = this.chatroom.title,
    unread = this.chatroom.unread,
    content = this.chatroom.content,
    createdAt = this.chatroom.updatedAt,
    images = this.images.map { it.profileImg },
    type = when(this.chatroom.type){
        1 -> ChatRoomType.ONE
        else -> ChatRoomType.MULTIPLE
    }
)