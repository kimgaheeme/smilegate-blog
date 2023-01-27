package com.smilegateblog.smilegateteamprojecttest.data.repository

import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.MemberDao
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Member
import com.smilegateblog.smilegateteamprojecttest.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow

class MemberRepositoryImpl(
    private val memberDao: MemberDao
): MemberRepository {
    override fun loadChatMemberImage(chatroomId: String): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override fun loadChatMember(chatroomId: String): Flow<List<Member>> {
        TODO("Not yet implemented")
    }
}