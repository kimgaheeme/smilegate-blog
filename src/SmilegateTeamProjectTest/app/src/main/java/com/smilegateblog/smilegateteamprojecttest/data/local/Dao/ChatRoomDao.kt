package com.smilegateblog.smilegateteamprojecttest.data.local.Dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatRoomDao {
    @Query(
        "SELECT title FROM chatroom WHERE chatroom_id = :chatroomId"
    )
    fun loadChatRoomTitle(chatroomId: String): Flow<String>
}