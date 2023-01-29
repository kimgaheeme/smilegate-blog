package com.smilegateblog.smilegateteamprojecttest.ui.viewmodel.main

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smilegateblog.smilegateteamprojecttest.domain.model.ChatRoomType
import com.smilegateblog.smilegateteamprojecttest.domain.model.Member
import com.smilegateblog.smilegateteamprojecttest.domain.model.People
import com.smilegateblog.smilegateteamprojecttest.domain.model.toPeople
import com.smilegateblog.smilegateteamprojecttest.domain.repository.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddChatMemberViewModel @Inject constructor(
    private val friendRepository: FriendRepository
): ViewModel() {

    var addChatMemberState = MutableStateFlow(AddChatMemeberState(chatId = "chatId"))
        private set

    init {
        getFriendList()
    }

    private fun getFriendList() {
        viewModelScope.launch {
            friendRepository.loadFriend().collect { result ->
                addChatMemberState.update {
                    it.copy(friends = result.map { it.toPeople() })
                }
            }
        }
    }

    private fun getFriendByNickname() {
        viewModelScope.launch {
            friendRepository.loadFriendByNickname(nickname = addChatMemberState.value.query.text)
                .collectLatest { result ->
                addChatMemberState.update {
                    it.copy(result = result.map { it.toPeople() })
                }
            }
        }
    }

    fun addPeople(people: People) {
        addChatMemberState.update {
            it.copy(checkedPeople = addChatMemberState.value.checkedPeople.plus(people))
        }
    }

    fun deletePeople(people: People) {
        addChatMemberState.update {
            it.copy(checkedPeople = addChatMemberState.value.checkedPeople.minusElement(people))
        }
    }

    fun setQuery(query: TextFieldValue) {
        addChatMemberState.update {
            it.copy(query = query)
        }
        if(query != TextFieldValue("")) getFriendByNickname()
    }

    fun setFocusState(isFocus: Boolean) {
        addChatMemberState.update {
            it.copy(textFieldFocusState = isFocus)
        }
    }
}

data class AddChatMemeberState(
    val checkedPeople: List<People> = emptyList(),
    val friends: List<People> = emptyList(),
    var query: TextFieldValue = TextFieldValue(""),
    val result: List<People> = emptyList(),
    val textFieldFocusState: Boolean = false,
    val members: List<Member> = emptyList(),
    val chatRoomType: ChatRoomType = ChatRoomType.ONE,
    val chatId: String
)

