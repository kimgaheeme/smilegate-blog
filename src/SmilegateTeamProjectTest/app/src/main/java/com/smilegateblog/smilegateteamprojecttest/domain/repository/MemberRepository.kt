package com.smilegateblog.smilegateteamprojecttest.domain.repository

import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Member
import kotlinx.coroutines.flow.Flow

interface MemberRepository {
    fun loadChatMemberImage(chatroomId: String): Flow<List<String>>
    fun loadChatMember(chatroomId: String): Flow<List<Member>>
}