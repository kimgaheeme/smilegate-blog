package com.smilegateblog.smilegateteamprojecttest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.smilegateblog.smilegateteamprojecttest.data.local.Dao.*
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.ChatRoom
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Friend
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Member
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Message

@Database(entities = [ChatRoom::class, Friend::class, Member::class, Message::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val chatroomDao: ChatRoomDao
    abstract val chatroomMessageDao: ChatRoomMessageDao
    abstract val friendDao: FriendDao
    abstract val memberDao: MemberDao
    abstract val messageDao: MessageDao

    companion object {
        const val DATABASE_NAME = "smg-db"
    }
}