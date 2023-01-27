package com.smilegateblog.smilegateteamprojecttest.domain.usecase

import android.util.Log
import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.ChatRoomMessage
import com.smilegateblog.smilegateteamprojecttest.domain.model.ChatRoom
import com.smilegateblog.smilegateteamprojecttest.domain.model.ChatRoomType
import com.smilegateblog.smilegateteamprojecttest.domain.repository.ChatRoomRepository
import com.smilegateblog.smilegateteamprojecttest.domain.repository.MemberRepository
import com.smilegateblog.smilegateteamprojecttest.ui.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetChatRoomAndMessageUseCase @Inject constructor(
    private val chatRoomRepository: ChatRoomRepository,
    private val memberRepository: MemberRepository
){
    operator fun invoke(): Flow<Resource<List<ChatRoom>>> = flow {
        emit(Resource.Loading())


        try {
            var chatroomResponse: List<ChatRoomMessage> = emptyList()
            chatRoomRepository.loadChatRoomAndMessage().collectLatest { result ->
                chatroomResponse = result
            }

            var response: MutableList<ChatRoom> = emptyList<ChatRoom>() as MutableList<ChatRoom>
            chatroomResponse.forEach{
                memberRepository.loadChatMemberImage(it.chatroomId).collect { images ->
                    response.add(ChatRoom(
                        chatroomId = it.chatroomId,
                        title = it.title,
                        unread = it.unread,
                        content = it.content,
                        createdAt = it.createdAt,
                        type = when(it.type){
                            1 -> ChatRoomType.ONE
                            else -> ChatRoomType.MULTIPLE
                        },
                        images = images
                    ))
                }
            }
            
            emit(Resource.Success(response))

        } catch (e: Exception) {
            Log.d("Error", "GetChatRoomAndMessageUseCase")
        }
    }
}