package com.smilegateblog.smilegateteamprojecttest.data.local.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


/**
 * type
 * 1 -> One
 * 2 -> Multiple
 */
@Entity(
    tableName = "chatroom",
    foreignKeys = [
        ForeignKey(
            entity = Message::class,
            parentColumns = ["message_id"],
            childColumns = ["last_message"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class ChatRoom(
    @PrimaryKey
    @ColumnInfo(name = "chatroom_id")
    val chatroomId: String,
    val title: String,
    var unread: Int,
    @ColumnInfo(name = "last_message")
    var lastMessage: String? = null,
    var type: Int
)