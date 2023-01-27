package com.smilegateblog.smilegateteamprojecttest.domain.repository

import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.ChatRoomMessage
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.ChatRoom
import kotlinx.coroutines.flow.Flow

interface ChatRoomRepository {
    fun loadChatRoomTitle(chatroomId: String): Flow<String>
    fun loadChatRoomAndMessage(): Flow<List<ChatRoomMessage>>
}