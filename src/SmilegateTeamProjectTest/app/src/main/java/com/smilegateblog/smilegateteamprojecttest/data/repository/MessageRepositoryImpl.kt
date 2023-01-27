package com.smilegateblog.smilegateteamprojecttest.data.repository

import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.MessageDao
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Message
import com.smilegateblog.smilegateteamprojecttest.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow

class MessageRepositoryImpl(
    private val messageDao: MessageDao
): MessageRepository {
    override fun loadChatMessage(chatroomId: String): Flow<List<Message>> {
        TODO("Not yet implemented")
    }
}