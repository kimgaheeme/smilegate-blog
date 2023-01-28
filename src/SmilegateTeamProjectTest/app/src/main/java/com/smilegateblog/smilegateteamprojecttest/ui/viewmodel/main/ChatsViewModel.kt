package com.smilegateblog.smilegateteamprojecttest.ui.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smilegateblog.smilegateteamprojecttest.domain.model.ChatRoom
import com.smilegateblog.smilegateteamprojecttest.domain.model.toChatRoom
import com.smilegateblog.smilegateteamprojecttest.domain.repository.ChatRoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val chatRoomRepository: ChatRoomRepository
): ViewModel() {
    var chatsState = MutableStateFlow(ChatsState())
        private set

    init {
        getChats()
    }

    private fun getChats() {
        viewModelScope.launch {
            chatRoomRepository.loadChatRoomAndMessage().collect { result ->
                var chats = result.map {
                    it.toChatRoom()
                }
                chatsState.update {
                    it.copy(chats = chats)
                }
            }
        }
    }
}

data class ChatsState(
    val chats: List<ChatRoom> = emptyList()
)