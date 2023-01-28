package com.smilegateblog.smilegateteamprojecttest.data.local.Dao

import androidx.room.Dao
import androidx.room.Query
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Friend
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendDao {
    @Query(
        "SELECT * FROM friends WHERE status = 1 ORDER BY nickname ASC"
    )
    fun loadFriend(): Flow<List<Friend>>

    @Query(
        "SELECT * FROM friends " +
        "WHERE status = 1 AND nickname LIKE :nickname " +
        "ORDER BY nickname ASC"
    )
    fun loadFriendByNickname(nickname: String): Flow<List<Friend>>
}