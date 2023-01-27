package com.smilegateblog.smilegateteamprojecttest.data.repository

import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.FriendDao
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Friend
import com.smilegateblog.smilegateteamprojecttest.domain.repository.FriendRepository
import kotlinx.coroutines.flow.Flow

class FriendRepositoryImpl(
    private val friendDao: FriendDao
): FriendRepository {
    override fun loadFriend(): Flow<List<Friend>> {
        return friendDao.loadFriend()
    }
}