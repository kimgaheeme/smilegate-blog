package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smilegateblog.smilegateteamprojecttest.R
import com.smilegateblog.smilegateteamprojecttest.model.People
import com.smilegateblog.smilegateteamprojecttest.ui.component.*
import com.smilegateblog.smilegateteamprojecttest.ui.util.KeyLine
import com.smilegateblog.smilegateteamprojecttest.ui.util.SearchDisplay

object AddChatScreenValue {
    val BetweenFriendPaddingSize = 10.dp
    val ItemHeight = 56.dp
    const val ProfileSize = 41
    val ProfileDividerPadding = 2.dp
    val SmallIconBtnSize = 21.dp
}

@Composable
fun AddChat(
    upPress: () -> Unit,
    navigateToNewChat: () -> Unit,
    navigateToAddGroupChat: () -> Unit
) {
    var query by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
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
    var searchDisplay : SearchDisplay = when {
        query == TextFieldValue("") -> SearchDisplay.Default
        result.isNotEmpty() -> SearchDisplay.Results
        else -> SearchDisplay.NoResults
    }


    Column(
        modifier = Modifier
            .padding(horizontal = KeyLine)
    ) {
        MainTopBarWithLeftBtn(
            onLeftBtnClick = upPress,
            content = stringResource(id = R.string.add_chat_top_bar),
            leftContent = stringResource(id = R.string.add_chat_cancel_btn)
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

        when(searchDisplay) {
            SearchDisplay.NoResults -> {
                NoResultScreen()
            }
            SearchDisplay.Default -> {
                PeopleList(
                    result = default,
                    onClick = { navigateToNewChat() }
                ) {
                    Row(
                        modifier = Modifier
                            .clickable { navigateToAddGroupChat() }
                            .fillMaxWidth()
                            .height(AddChatScreenValue.ItemHeight),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        IconButton(
                            onClick = {},
                            enabled = false,
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colors.secondary,
                                    CircleShape
                                )
                                .size(AddChatScreenValue.ProfileSize.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(AddChatScreenValue.SmallIconBtnSize),
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_groups),
                                contentDescription = null,
                                tint = MaterialTheme.colors.onBackground
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.add_chat_create_group_bar),
                            fontSize = 17.sp,
                            modifier = Modifier.weight(1f)
                        )

                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = null)
                    }
                    SubTitle(content = stringResource(id = R.string.add_chat_friend_subtitle))
                }
            }
            SearchDisplay.Results -> {
                PeopleList(
                    result = result,
                    onClick = navigateToNewChat
                ){}
            }
        }
    }
}

@Composable
private fun PeopleList(
    result: List<People>,
    onClick: () -> Unit,
    content: @Composable () -> Unit
){
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(AddChatScreenValue.BetweenFriendPaddingSize)
    ) {
        item { content() }

        items(result) { friend ->
            PeopleItem(
                imageURL = friend.profileImage,
                nickname = friend.nickname,
                modifier = Modifier.clickable { onClick() },
                profileSize = AddChatScreenValue.ProfileSize
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = AddChatScreenValue.ProfileSize.dp,
                        top = AddChatScreenValue.ProfileDividerPadding,
                        bottom = AddChatScreenValue.ProfileDividerPadding
                    )
            )
        }
    }
}