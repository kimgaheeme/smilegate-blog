package com.smilegateblog.smilegateteamprojecttest.ui.viewmodel.main

import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smilegateblog.smilegateteamprojecttest.domain.model.*
import com.smilegateblog.smilegateteamprojecttest.domain.repository.ChatRoomRepository
import com.smilegateblog.smilegateteamprojecttest.domain.repository.MemberRepository
import com.smilegateblog.smilegateteamprojecttest.domain.repository.MessageRepository
import com.smilegateblog.smilegateteamprojecttest.ui.navigation.DestinationID
import com.smilegateblog.smilegateteamprojecttest.ui.screen.main.InputSelector
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    private val messageRepository: MessageRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var chatState = MutableStateFlow(ChatState(chatroomId = savedStateHandle.get<String>(DestinationID.CHAT_ID)?: null))
        private set

    init {
        if(!chatState.value.chatroomId.isNullOrBlank()){
            getMemberList()
            getMessageList()
        }

    }

    private fun getMessageList() {
        viewModelScope.launch {
            messageRepository.loadChatMessage(chatState.value.chatroomId!!).collect { result ->
                chatState.update {
                    it.copy(messages = result.map { it.toMessage() })
                }
            }
        }
    }

    private fun getMemberList() {
        viewModelScope.launch {
            memberRepository.loadChatMember(chatState.value.chatroomId!!).collect { result ->
                chatState.update {
                    it.copy(members = result.map { it.memberId to it.toMember() }.toMap())
                }
                Log.d("가희", chatState.value.members.values.map { it.profileImg }.size.toString())
            }
        }
    }

    fun setQuery(query: TextFieldValue) {
        chatState.update {
            it.copy(query = query)
        }
    }

    fun setFocusState(isFocus: Boolean) {
        chatState.update {
            it.copy(textFieldFocusState = isFocus)
        }
    }

    fun setInputSelector(inputSelector: InputSelector) {
        chatState.update {
            it.copy(currentInputSelector = inputSelector)
        }
    }
}

data class ChatState(
    val members: Map<String, Member> = mapOf(),
    val messages: List<Message> = emptyList(),
    var chatroomId: String? = null,
    val title: String = "",
    var query: TextFieldValue = TextFieldValue(""),
    val textFieldFocusState: Boolean = false,
    val currentInputSelector: InputSelector = InputSelector.NONE,
    val chatRoomType: ChatRoomType = ChatRoomType.ONE

)
