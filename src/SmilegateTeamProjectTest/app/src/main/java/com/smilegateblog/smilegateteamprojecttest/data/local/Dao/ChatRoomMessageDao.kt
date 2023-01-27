package com.smilegateblog.smilegateteamprojecttest.data.local.Dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ChatRoomMessageDao {
    @Query(
        "SELECT chatroom.chatroom_id AS chatroomId, " +
                "chatroom.title AS title, " +
                "chatroom.unread AS unread, " +
                "chatroom.type AS type, " +
                "messages.content AS content, " +
                "messages.created_at AS createdAt " +
        "FROM chatroom, messages " +
        "WHERE chatroom.chatroom_id = messages.message_id " +
        "ORDER BY messages.created_at ASC"
    )
    fun loadChatAndMessage(): Flow<List<ChatRoomMessage>>
}

data class ChatRoomMessage(
    val chatroomId: String,
    val title: String,
    val unread: Int,
    val type: Int,
    val content: String,
    val createdAt: Date
)