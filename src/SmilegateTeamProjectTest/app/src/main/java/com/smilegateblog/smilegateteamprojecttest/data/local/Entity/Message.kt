package com.smilegateblog.smilegateteamprojecttest.data.local.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = ChatRoom::class,
            parentColumns = ["chatroom_id"],
            childColumns = ["chatroom_id"]
        ),
        ForeignKey(
            entity = Member::class,
            parentColumns = ["member_id"],
            childColumns = ["message_from_id"]
        ),
    ]
)
data class Message(
    @PrimaryKey
    @ColumnInfo(name = "message_id")
    val messageId: String,
    @ColumnInfo(name = "message_from_id")
    val messageFromID: String,
    @ColumnInfo(name = "chatroom_id")
    val chatroomId: String,
    var content: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String
)
