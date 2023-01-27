package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smilegateblog.smilegateteamprojecttest.R
import com.smilegateblog.smilegateteamprojecttest.model.ChatRooms
import com.smilegateblog.smilegateteamprojecttest.model.Member
import com.smilegateblog.smilegateteamprojecttest.model.Message
import com.smilegateblog.smilegateteamprojecttest.model.MessageType
import com.smilegateblog.smilegateteamprojecttest.ui.component.*
import com.smilegateblog.smilegateteamprojecttest.ui.util.KeyLine
import java.time.format.DateTimeFormatter

enum class InputSelector {
    NONE,
    PICTURE,
    CAMERA
}

object ChatScreenValue {
    var ChatBarIconSize = 28.dp
    var ChatBarIconBtnSize = 34.dp
    const val ProfileImageSize = 104
    var ProfileImageBottomPadding = 10.dp
    var IconBtnSize = 38.dp
    var SmallIconBtnSize = 18.dp
    var SpaceBetweenButtons1 = 30.dp
    var SpaceBetweenButtons2 = 22.dp
    val TopBarHeight = 46.dp
    val ChatBarPadding = 12.dp
    val UserInputHeightSize = 52.dp
}

@Composable
fun Chat(
    upPress: () -> Unit,
    navigateToChatInfo: (String) -> Unit,
    navigateToAddMember: (String) -> Unit,
) {
    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    var chatRooms by remember{ mutableStateOf(
        ChatRooms(
            "h","title","consdgfgdsdvzsdvsdvftentawjbefkajwbefkjabwe", DateTimeFormatter.ISO_DATE,3324,1, listOf(
                Member("asd","asdf",""),
                Member("asd","asdf2","")
            )
        )
    ) }

    var messages by remember{ mutableStateOf(listOf(
        Message("12",1L,"123","1234asd", DateTimeFormatter.ISO_DATE,MessageType.TEXT,""),
        Message("13",1L,"123","1234asdf", DateTimeFormatter.ISO_DATE,MessageType.TEXT,""),
        Message("14",1L,"123","1234asdg", DateTimeFormatter.ISO_DATE,MessageType.TEXT,""),
        Message("15",0L,"123","1234qwe", DateTimeFormatter.ISO_DATE,MessageType.TEXT,""),
        Message("16",1L,"123","1234qwet", DateTimeFormatter.ISO_DATE,MessageType.TEXT,""),
        Message("17",0L,"123","1234wey", DateTimeFormatter.ISO_DATE,MessageType.TEXT,""),
        Message("18",1L,"123","1234qw", DateTimeFormatter.ISO_DATE,MessageType.TEXT,""),
        Message("19",1L,"123","1234hdf", DateTimeFormatter.ISO_DATE,MessageType.TEXT,""),
        Message("20",0L,"123","1234re", DateTimeFormatter.ISO_DATE,MessageType.TEXT,""),
        Message("21",1L,"123","1234es", DateTimeFormatter.ISO_DATE,MessageType.TEXT,""),
        Message("22",0L,"123","1234wy", DateTimeFormatter.ISO_DATE,MessageType.TEXT,""),
        Message("23",0L,"123","1234qw", DateTimeFormatter.ISO_DATE,MessageType.TEXT,"")
    )) }

    var textFieldFocusState by remember { mutableStateOf(false) }
    var currentInputSelector by rememberSaveable{ mutableStateOf( InputSelector.NONE )}
    val dismissKeyboard = { currentInputSelector = InputSelector.NONE }
    var focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = KeyLine)
    ) {
        Column(
            modifier = Modifier.clickable {
                if(textFieldFocusState) {
                    textFieldFocusState = false
                    focusManager.clearFocus()
                }
            }
        ) {
            Messages(
                messages = messages,
                onAuthorClick = {},
                onImageClick = {},
                onVideoClick = {},
                isGroupChat = false,
                modifier = Modifier
                    .padding(
                        top = ChatScreenValue.TopBarHeight,
                        bottom = ChatScreenValue.UserInputHeightSize
                    )
                    .fillMaxWidth()
            ) {
                if(chatRooms.members.size > 1) {
                    GroupChatButtons(
                        onAddBtnClick = { navigateToAddMember(chatRooms.chatRoomId) },
                        onEditBtnClick = { /*TODO*/ },
                        onMemberBtnClick = {  }
                    )
                }
                ChatRoomInfo(
                    images = chatRooms.members.map { it.profileImage },
                    title = chatRooms.title
                )
            }
        }

        ChatTopBar(
            onClick = navigateToChatInfo,
            upPress = upPress,
            chatroomTitle = chatRooms.title,
            images = chatRooms.members.map { it.profileImage },
            chatId = chatRooms.chatRoomId,
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .align(Alignment.TopCenter)
        )

        UserInput(
            onMessageSent = { /*TODO*/ },
            resetScroll = { /*TODO*/ },
            query = textState,
            onQueryChange = { textState = it },
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .align(Alignment.BottomStart)
                .navigationBarsPadding()
                .imePadding(),
            onTextFieldFocused = { focused ->
                if (focused) {
                    currentInputSelector = InputSelector.NONE
                }
                textFieldFocusState = focused
            },
            focused = textFieldFocusState,
            currentInputSelector = currentInputSelector,
            onChangeCurrentInput = {
                currentInputSelector = it

                if(textFieldFocusState) {
                    textFieldFocusState = false
                    focusManager.clearFocus()
                }
            },
            dismissKeyboard = dismissKeyboard
        )
    }
}

@Composable
fun UserInput(
    onMessageSent:() -> Unit,
    resetScroll: () -> Unit,
    query: TextFieldValue,
    focused: Boolean,
    onTextFieldFocused: (Boolean) -> Unit,
    onQueryChange: (TextFieldValue) -> Unit,
    currentInputSelector: InputSelector,
    onChangeCurrentInput: (InputSelector) -> Unit,
    dismissKeyboard: () -> Unit,
    modifier: Modifier
) {

    Row(
        modifier = modifier
            .height(ChatScreenValue.UserInputHeightSize)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onChangeCurrentInput(InputSelector.CAMERA) },
            modifier = Modifier.size(ChatScreenValue.ChatBarIconBtnSize)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_camera_alt_24),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(ChatScreenValue.ChatBarIconSize)
            )
        }

        IconButton(
            onClick = { onChangeCurrentInput(InputSelector.PICTURE) },
            modifier = Modifier.size(ChatScreenValue.ChatBarIconBtnSize)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_photo_24),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(ChatScreenValue.ChatBarIconSize)
            )
        }

        ChatTextBar(
            query = query,
            onQueryChange = onQueryChange,
            focused = focused,
            onTextFieldFocused = onTextFieldFocused,
            keyboardShown = currentInputSelector == InputSelector.NONE && focused,
            modifier = Modifier
                .weight(1f)
                .padding(start = ChatScreenValue.ChatBarPadding)
        )

        if(query != TextFieldValue("")) {
            IconButton(
                onClick = {
                    onMessageSent()
                    resetScroll()
                    dismissKeyboard()
                          },
                modifier = Modifier
                    .padding(start = ChatScreenValue.ChatBarPadding)
                    .size(ChatScreenValue.ChatBarIconBtnSize)

            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(ChatScreenValue.ChatBarIconSize)
                )
            }
        }
    }
}

@Composable
private fun ChatRoomInfo(
    images: List<String>,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ProfileImages(
            images = images,
            profileSize = ChatScreenValue.ProfileImageSize
        )

        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            modifier = Modifier
                .padding(bottom = ChatScreenValue.ProfileImageBottomPadding)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun GroupChatButtons(
    onAddBtnClick:() -> Unit,
    onEditBtnClick:() -> Unit,
    onMemberBtnClick:() -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                IconButton(
                    onClick = onAddBtnClick,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.secondary,
                            CircleShape
                        )
                        .size(ChatScreenValue.IconBtnSize)
                ) {
                    Icon(
                        modifier = Modifier.size(ChatScreenValue.SmallIconBtnSize),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_person_add),
                        contentDescription = null
                    )
                }

                Text(
                    text = stringResource(id = R.string.chat_add_btn),
                    modifier = Modifier.padding(2.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.size(ChatScreenValue.SpaceBetweenButtons1))

            Column( horizontalAlignment = Alignment.CenterHorizontally ) {
                IconButton(
                    onClick = onEditBtnClick,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.secondary,
                            CircleShape
                        )
                        .size(ChatScreenValue.IconBtnSize)
                ) {
                    Icon(
                        modifier = Modifier.size(ChatScreenValue.SmallIconBtnSize),
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null
                    )
                }

                Text(
                    text = stringResource(id = R.string.chat_edit_btn),
                    modifier = Modifier.padding(2.dp),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.size(ChatScreenValue.SpaceBetweenButtons2))

            Column( horizontalAlignment = Alignment.CenterHorizontally ) {
                IconButton(
                    onClick = onMemberBtnClick,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.secondary,
                            CircleShape
                        )
                        .size(ChatScreenValue.IconBtnSize)
                ) {
                    Icon(
                        modifier = Modifier.size(ChatScreenValue.SmallIconBtnSize),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_groups),
                        contentDescription = null
                    )
                }

                Text(
                    text = stringResource(id = R.string.chat_member_btn),
                    modifier = Modifier.padding(vertical = 2.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}