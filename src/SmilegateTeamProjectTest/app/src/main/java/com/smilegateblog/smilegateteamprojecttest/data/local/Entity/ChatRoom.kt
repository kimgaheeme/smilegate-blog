package com.smilegateblog.smilegateteamprojecttest.data.local.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * type
 * 1 -> One
 * 2 -> Multiple
 */
@Entity(tableName = "chatroom")
data class ChatRoom(
    @PrimaryKey
    @ColumnInfo(name = "chatroom_id")
    val chatroomId: String,
    val title: String,
    var unread: Int,
    var content: String = "",
    @ColumnInfo(name = "updated_at")
    var updatedAt: Date,
    var type: Int
)