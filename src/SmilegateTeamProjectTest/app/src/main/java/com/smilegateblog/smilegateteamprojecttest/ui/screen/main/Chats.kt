package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smilegateblog.smilegateteamprojecttest.R
import com.smilegateblog.smilegateteamprojecttest.model.ChatRoom
import com.smilegateblog.smilegateteamprojecttest.model.Member
import com.smilegateblog.smilegateteamprojecttest.ui.component.*
import com.smilegateblog.smilegateteamprojecttest.ui.theme.SmilegateTeamProjectTestTheme
import com.smilegateblog.smilegateteamprojecttest.ui.util.Constants
import com.smilegateblog.smilegateteamprojecttest.ui.util.KeyLine
import java.time.format.DateTimeFormatter

object ChatsScreenValue {
    val ProfileStateItemWidth = 56.dp
    val SpacerBetweenProfileAndMessage = 7.dp
    val SpacerBetweenMessageAndUnread = 17.dp
    val UnreadSize = 23.dp
    val MessageDateSize = 50.dp

    val SpacerBetweenChatRooms = 12.dp
}


@Composable
fun Chats(
    navigateToChat: (String) -> Unit = {},
    navigateToAddChat: () -> Unit = {}
) {
    var query by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    var chatRooms by remember{ mutableStateOf(listOf(
        ChatRoom(
            "h","title","consdgfgdsdvzsdvsdvftentawjbefkajwbefkjabwe", DateTimeFormatter.ISO_DATE,3324, listOf(Member("asd","asdf",""))
        )
    )) }
    var activePeople by remember{ mutableStateOf(listOf<Member>(
        Member("asd","asdf","")
    )) }

    var textFieldFocusState by remember { mutableStateOf(false) }
    var focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = KeyLine)
    ) {
        TopBarWithProfile(
            onClick = { navigateToAddChat() },
            profileImage = "",
            content = stringResource(id = R.string.chats_title),
            icon = Icons.Filled.Edit
        )

        LazyColumn {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = { query = it },
                    searchFocused = textFieldFocusState,
                    onSearchFocusChange = { textFieldFocusState = it },
                    onClearQuery = {
                        focusManager.clearFocus()
                        query = TextFieldValue("")
                    })
            }

            item {
                LazyRow(
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    items(activePeople) {
                        ProfileStateItem(
                            imageURL = it.profileImage,
                            nickname = it.nickname
                        )
                    }
                }
            }

            if(chatRooms.isEmpty()) {
                item {
                    Text("빈화면임")
                }
            } else {
                items(chatRooms) {
                    ChatItem(
                        onClick = navigateToChat,
                        chatRoom = it,
                        isActivate = null)
                    Spacer(modifier = Modifier.size(ChatsScreenValue.SpacerBetweenChatRooms))
                }
            }
        }
    }
}

@Composable
fun EmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "채팅이 없습니다.",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ProfileStateItem(
    imageURL: String,
    isActivate: Boolean = true,
    nickname: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProfileImageWithState(imageURL = imageURL, isActivate = isActivate)
        Text(
            text = nickname,
            fontSize = 14.sp,
            maxLines = 2,
            modifier = Modifier.width(ChatsScreenValue.ProfileStateItemWidth),
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ChatItem(
    onClick: (String) -> Unit,
    chatRoom: ChatRoom,
    isActivate: Boolean?
) {
    var unread = if(chatRoom.unread >= Constants.maxNewMessage) {
        Constants.maxNewMessage.toString() + "+"
    } else {
        chatRoom.unread.toString()
    }



    Row(
        modifier = Modifier
            .clickable { onClick(chatRoom.chatRoomId) }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(chatRoom.members.size == 1) {
            ProfileImageWithState(
                imageURL = chatRoom.members.first().profileImage,
                isActivate = isActivate?: false,
                profileSize = ProfileImageValue.ProfileWithStateSize
            )
        } else {
            ProfileImages(
                images = chatRoom.members.map { it.profileImage },
                profileSize = ProfileImageValue.ProfileWithStateSize
            )
        }

        Spacer(modifier = Modifier.size(ChatsScreenValue.SpacerBetweenProfileAndMessage))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = chatRoom.title,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row() {
                Text(
                    text = chatRoom.lastMessageContent,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                )

                Text(
                    text = chatRoom.lastMessageCreatedAt.toString(),
                    fontSize = 14.sp,
                    maxLines = 1,
                    modifier = Modifier.width(ChatsScreenValue.MessageDateSize)
                )
            }
        }

        Spacer(modifier = Modifier.size(ChatsScreenValue.SpacerBetweenMessageAndUnread))
        
        Text(
            text = unread,
            fontSize = 14.sp,
            color = MaterialTheme.colors.onPrimary,
            maxLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .background(MaterialTheme.colors.primary, CircleShape)
                .defaultMinSize(minWidth = ChatsScreenValue.UnreadSize)
                .padding(3.dp)
        )

    }
}

@Composable
@Preview
fun PreviewProfile() {
    SmilegateTeamProjectTestTheme() {
        ProfileStateItem(imageURL = "", nickname = "asdjfgakhsjd")
    }
}

@Composable
@Preview
fun PreviewChatsScreen() {
    SmilegateTeamProjectTestTheme() {
        Chats()
    }
}