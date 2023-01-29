package com.smilegateblog.smilegateteamprojecttest.domain.repository

import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun loadChatMessage(chatroomId: String): Flow<List<Message>>
}