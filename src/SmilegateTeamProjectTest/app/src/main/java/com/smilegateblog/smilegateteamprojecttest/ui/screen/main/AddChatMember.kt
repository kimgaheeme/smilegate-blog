package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.smilegateblog.smilegateteamprojecttest.R
import com.smilegateblog.smilegateteamprojecttest.domain.model.ChatRoomType
import com.smilegateblog.smilegateteamprojecttest.domain.model.People
import com.smilegateblog.smilegateteamprojecttest.model.Member
import com.smilegateblog.smilegateteamprojecttest.ui.component.*
import com.smilegateblog.smilegateteamprojecttest.ui.util.KeyLine
import com.smilegateblog.smilegateteamprojecttest.ui.util.SearchDisplay
import com.smilegateblog.smilegateteamprojecttest.ui.viewmodel.main.AddChatMemberViewModel

object AddChatMemberValue {
    val BetweenFriendPaddingSize = 10.dp
    val BetweenFriendAndCheckedSize = 10.dp
}

@Composable
fun AddChatMember(
    upPress: () -> Unit,
    navigateToNewChat: () -> Unit,
    navigateToUpdateGroupChat: (String) -> Unit
) {
    val viewModel = hiltViewModel<AddChatMemberViewModel>()
    val state by viewModel.addChatMemberState.collectAsState()
    val query = state.query
    val result = state.result
    var focusManager = LocalFocusManager.current

    //멤버수에 따른 함수
    val addMember: () -> Unit = if(state.chatRoomType == ChatRoomType.ONE) { { navigateToNewChat() } }
        else { { navigateToUpdateGroupChat(state.chatId) } }

    var searchDisplay : SearchDisplay = when {
        query == TextFieldValue("") -> SearchDisplay.Default
        result.isNotEmpty() -> SearchDisplay.Results
        else -> SearchDisplay.NoResults
    }


    Column(
        modifier = Modifier
            .padding(horizontal = KeyLine)
    ) {
        MainTopBarWithBothBtn(
            onLeftBtnClick = upPress,
            onRightBtnClick = addMember,
            content = stringResource(id = R.string.add_chat_member_top_bar),
            leftContent = stringResource(id = R.string.add_chat_member_cancel_btn),
            rightContent = stringResource(id = R.string.add_chat_member_add_btn),
            rightVisible = state.checkedPeople.isNotEmpty()
        )

        SearchBar(
            query = query,
            onQueryChange = viewModel::setQuery,
            searchFocused = state.textFieldFocusState,
            onSearchFocusChange = viewModel::setFocusState,
            onClearQuery = {
                focusManager.clearFocus()
                viewModel.setQuery(TextFieldValue(""))
            },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyRow(
            modifier = Modifier.padding(vertical = AddChatMemberValue.BetweenFriendAndCheckedSize),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(state.checkedPeople) { friend ->
                ProfileWithDeleteBtn(
                    imageURL = friend.profileImg,
                    onClick = { viewModel.deletePeople(people = friend) }
                )
            }
        }

        when(searchDisplay) {
            SearchDisplay.NoResults -> {
                NoResultScreen()
            }
            SearchDisplay.Default -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(AddChatMemberValue.BetweenFriendPaddingSize)
                ) {
                    item { 
                        SubTitle(content = stringResource(id = R.string.add_chat_member_friend_subtitle))
                    }
                    items(state.friends) { friend ->
                        PeopleWithCheckItem(
                            onClick = {
                                if(friend in state.checkedPeople) viewModel.deletePeople(people = friend)
                                else viewModel.addPeople(people = friend)
                            },
                            imageURL = friend.profileImg,
                            nickname = friend.nickname,
                            isChecked = state.checkedPeople.contains(friend)
                        )
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = PeopleItemValue.ProfileSize.dp
                                )
                        )
                    }
                }
            }
            SearchDisplay.Results -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(AddChatMemberValue.BetweenFriendPaddingSize)
                ) {
                    items(result) { friend ->
                        PeopleWithCheckItem(
                            onClick = {
                                if(friend in state.checkedPeople) viewModel.deletePeople(people = friend)
                                else viewModel.addPeople(people = friend)
                            },
                            imageURL = friend.profileImg,
                            nickname = friend.nickname,
                            isChecked = state.checkedPeople.contains(friend)
                        )
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = PeopleItemValue.ProfileSize.dp,
                                )
                        )
                    }
                }
            }
        }
    }

}


@Composable
fun NoResultScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            "No Result",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
