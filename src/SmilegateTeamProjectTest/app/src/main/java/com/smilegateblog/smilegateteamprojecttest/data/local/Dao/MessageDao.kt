package com.smilegateblog.smilegateteamprojecttest.data.local.Dao

import androidx.room.Dao
import androidx.room.Query
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query(
        "SELECT * FROM messages WHERE chatroom_id = :chatroomId ORDER BY created_at ASC"
    )
    fun loadChatMessage(chatroomId: String): Flow<List<Message>>
}