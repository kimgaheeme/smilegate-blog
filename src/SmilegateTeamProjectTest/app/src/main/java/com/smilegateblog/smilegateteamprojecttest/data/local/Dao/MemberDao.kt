package com.smilegateblog.smilegateteamprojecttest.data.local.Dao

import androidx.room.Dao
import androidx.room.Query
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {
    @Query(
        "SELECT profile_img FROM members WHERE chatroom_id = :chatroomId"
    )
    fun loadChatMemberImage(chatroomId: String): Flow<List<String>>

    @Query(
        "SELECT * FROM members WHERE chatroom_id = :chatroomId"
    )
    fun loadChatMember(chatroomId: String): Flow<List<Member>>
}