package com.smilegateblog.smilegateteamprojecttest.domain.usecase

import com.smilegateblog.smilegateteamprojecttest.domain.repository.ChatRoomRepository
import com.smilegateblog.smilegateteamprojecttest.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatRoomTitleUseCase @Inject constructor(
    private val repository: ChatRoomRepository
) {
    operator fun invoke(chatroomId: String): Flow<String> {
        return repository.loadChatRoomTitle(chatroomId = chatroomId)
    }
}