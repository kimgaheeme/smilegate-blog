package com.smilegateblog.smilegateteamprojecttest.data.local.Dao

import androidx.room.*
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.ChatRoom
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Member
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Message
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ChatRoomMessageDao {

    @Transaction
    @Query(
        "SELECT * " +
        "FROM chatroom "
    )
    fun loadChatRoomAndMessage(): Flow<List<ChatRoomMessage>>
}

data class ChatRoomMessage(
    @Embedded val chatroom: ChatRoom,

    @Relation(
        parentColumn = "chatroom_id",
        entityColumn = "chatroom_id"
    )
    val images: List<Member>,
)