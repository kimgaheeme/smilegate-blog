package com.smilegateblog.smilegateteamprojecttest.domain.usecase

import android.util.Log
import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.ChatRoomMessage
import com.smilegateblog.smilegateteamprojecttest.domain.model.ChatRoom
import com.smilegateblog.smilegateteamprojecttest.domain.model.ChatRoomType
import com.smilegateblog.smilegateteamprojecttest.domain.model.Message
import com.smilegateblog.smilegateteamprojecttest.domain.repository.ChatRoomRepository
import com.smilegateblog.smilegateteamprojecttest.domain.repository.MemberRepository
import com.smilegateblog.smilegateteamprojecttest.ui.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class GetChatRoomAndMessageUseCase @Inject constructor(
    private val chatRoomRepository: ChatRoomRepository,
    private val memberRepository: MemberRepository
){
    operator fun invoke(): Flow<Resource<List<ChatRoom>>> = flow {
        emit(Resource.Loading())

        try {
            chatRoomRepository.loadChatRoomAndMessage().collect(){
                val response = it
                val result = response.map {

                    ChatRoom(
                        chatroomId = it.chatroom.chatroomId,
                        title = it.chatroom.title,
                        unread = it.chatroom.unread,
                        content = "content",
                        createdAt = Date(),
                        images = it.images.map { it.profileImg },
                        type = when(it.chatroom.type){
                            1 -> ChatRoomType.ONE
                            else -> ChatRoomType.MULTIPLE
                        }
                    )
                }
                emit(Resource.Success(result))
            }
        } catch (e: Exception) {
            Log.d("Error", "GetChatRoomAndMessageUseCase")
        }
    }
}