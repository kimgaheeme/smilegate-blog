package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import android.util.Log
import androidx.compose.foundation.clickable
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
import com.smilegateblog.smilegateteamprojecttest.model.Member
import com.smilegateblog.smilegateteamprojecttest.model.People
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
    var query by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    val viewModel = hiltViewModel<AddChatMemberViewModel>()

    //검색결과
    val result by remember{ mutableStateOf(listOf<People>()) }
    //모든 친구 목록
    val default by remember{ mutableStateOf(listOf<People>(
        People("asd","nickname","", "", 1),
        People("asd","nickname1","", "", 1),
        People("asd","nickname3","", "", 1)
    )) }

    var textFieldFocusState by remember { mutableStateOf(false) }
    var focusManager = LocalFocusManager.current

    //이미 추가되어있는 멤버
    var members by remember{ mutableStateOf(listOf(
        Member("asd","nickname",""),
        Member("asd","nickname2","")
    )) }

    var chatId = "chatId"

    //추가할 멤버
    val checkedPeople by viewModel.addChatMemberState.collectAsState()

    //멤버수에 따른 함수
    val addMember: () -> Unit = if(members.size <= 1) { { navigateToNewChat() } }
        else { { navigateToUpdateGroupChat(chatId) } }

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
            rightVisible = checkedPeople.checkedPeople.isNotEmpty()
        )

        SearchBar(
            query = query,
            onQueryChange = { query = it },
            searchFocused = textFieldFocusState,
            onSearchFocusChange = { textFieldFocusState = it },
            onClearQuery = {
                focusManager.clearFocus()
                query = TextFieldValue("")
            },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyRow(
            modifier = Modifier.padding(vertical = AddChatMemberValue.BetweenFriendAndCheckedSize),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(checkedPeople.checkedPeople) { friend ->
                ProfileWithDeleteBtn(
                    imageURL = friend.profileImage,
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
                    items(default) { friend ->
                        PeopleWithCheckItem(
                            onClick = {
                                if(friend in checkedPeople.checkedPeople) viewModel.deletePeople(people = friend)
                                else viewModel.addPeople(people = friend)
                            },
                            imageURL = friend.profileImage,
                            nickname = friend.nickname,
                            isChecked = checkedPeople.checkedPeople.contains(friend)
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
                                if(friend in checkedPeople.checkedPeople) viewModel.deletePeople(people = friend)
                                else viewModel.addPeople(people = friend)
                            },
                            imageURL = friend.profileImage,
                            nickname = friend.nickname,
                            isChecked = checkedPeople.checkedPeople.contains(friend)
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
