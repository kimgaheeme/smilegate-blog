package com.smilegateblog.smilegateteamprojecttest.data.repository

import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.ChatRoomDao
import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.ChatRoomMessage
import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.ChatRoomMessageDao
import com.smilegateblog.smilegateteamprojecttest.domain.repository.ChatRoomRepository
import kotlinx.coroutines.flow.Flow

class ChatRoomRepositoryImpl(
    private val chatRoomDao: ChatRoomDao,
    private val chatRoomMessageDao: ChatRoomMessageDao
): ChatRoomRepository {
    override fun loadChatRoomTitle(chatroomId: String): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun loadChatRoomAndMessage(): Flow<List<ChatRoomMessage>> {
        TODO("Not yet implemented")
    }
}