package com.smilegateblog.smilegateteamprojecttest.data.local.Dao

import androidx.room.*
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.ChatRoom
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Member
import com.smilegateblog.smilegateteamprojecttest.data.local.Entity.Message
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ChatRoomMemberImageDao {

    @Transaction
    @Query(
        "SELECT * " +
        "FROM chatroom ORDER BY chatroom.updated_at DESC "
    )
    fun loadChatRoomAndMessage(): Flow<List<ChatRoomMemberImage>>
}

data class ChatRoomMemberImage(
    @Embedded val chatroom: ChatRoom,

    @Relation(
        parentColumn = "chatroom_id",
        entityColumn = "chatroom_id"
    )
    val images: List<Member>,
)