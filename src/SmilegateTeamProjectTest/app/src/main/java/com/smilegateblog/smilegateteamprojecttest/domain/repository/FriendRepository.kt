package com.smilegateblog.smilegateteamprojecttest.domain.repository

import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Friend
import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    fun loadFriend(): Flow<List<Friend>>
    fun loadFriendByNickname(nickname: String): Flow<List<Friend>>
}