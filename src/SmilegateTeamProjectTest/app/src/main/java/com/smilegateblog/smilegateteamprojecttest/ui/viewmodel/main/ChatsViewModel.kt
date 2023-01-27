package com.smilegateblog.smilegateteamprojecttest.ui.viewmodel.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smilegateblog.smilegateteamprojecttest.domain.model.ChatRoom
import com.smilegateblog.smilegateteamprojecttest.domain.usecase.GetChatRoomAndMessageUseCase
import com.smilegateblog.smilegateteamprojecttest.ui.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val getChatRoomAndMessageUseCase: GetChatRoomAndMessageUseCase
): ViewModel() {
    var chatsState = MutableStateFlow(ChatsState())
        private set

    init {
        getChats()
    }

    private fun getChats() {
        viewModelScope.launch {
            getChatRoomAndMessageUseCase().collect { result ->
                when(result) {
                    is Resource.Success -> {
                        chatsState.update {
                            it.copy(chats = result.data!!)
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}

data class ChatsState(
    val chats: List<ChatRoom> = emptyList()
)