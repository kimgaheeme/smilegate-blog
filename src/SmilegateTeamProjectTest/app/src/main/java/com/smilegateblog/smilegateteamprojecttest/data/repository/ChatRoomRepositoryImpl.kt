package com.smilegateblog.smilegateteamprojecttest.data.repository

import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.ChatRoomDao
import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.ChatRoomMemberImage
import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.ChatRoomMemberImageDao
import com.smilegateblog.smilegateteamprojecttest.domain.repository.ChatRoomRepository
import kotlinx.coroutines.flow.Flow

class ChatRoomRepositoryImpl(
    private val chatRoomDao: ChatRoomDao,
    private val chatroomMemberImageDao: ChatRoomMemberImageDao
): ChatRoomRepository {
    override fun loadChatRoomTitle(chatroomId: String): Flow<String> {
        return chatRoomDao.loadChatRoomTitle(chatroomId)
    }

    override fun loadChatRoomAndMessage(): Flow<List<ChatRoomMemberImage>> {
        return chatroomMemberImageDao.loadChatRoomAndMessage()
    }
}