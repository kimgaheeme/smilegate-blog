package com.smilegateblog.smilegateteamprojecttest.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.smilegateblog.smilegateteamprojecttest.R
import com.smilegateblog.smilegateteamprojecttest.model.Message
import com.smilegateblog.smilegateteamprojecttest.model.MessageType
import com.smilegateblog.smilegateteamprojecttest.ui.util.SymbolAnnotationType
import com.smilegateblog.smilegateteamprojecttest.ui.util.messageFormatter

object ChatItemBubbleValue {
    val firstBubbleShape = RoundedCornerShape(18.dp, 18.dp, 18.dp, 4.dp)
    val lastBubbleShape = RoundedCornerShape(4.dp, 18.dp, 18.dp, 18.dp)
    val defaultBubbleShape = RoundedCornerShape(4.dp, 18.dp, 18.dp, 4.dp)
    val betweenBubbleShape = RoundedCornerShape(4.dp, 18.dp, 18.dp, 4.dp)
}

/**
 * author me 변경해야함!!
 * user id를 저장해서 비교하기
 */
@Composable
fun Messages(
    messages: List<Message>,
    onAuthorClick: (Long) -> Unit,
    onImageClick: (String) -> Unit,
    onVideoClick: (String) -> Unit,
    isGroupChat: Boolean,
    modifier: Modifier
) {
    val userId = 0L

    LazyColumn(
        reverseLayout = true,
        contentPadding =
            WindowInsets.statusBars.add(WindowInsets(top = 90.dp)).asPaddingValues(),
        modifier = modifier.fillMaxSize()
    ) {
        for(index in messages.indices) {
            val prevAuthor = messages.getOrNull(index - 1)?.authorId
            val nextAuthor = messages.getOrNull(index + 1)?.authorId
            val content = messages[index]
            val isFirstMessageByAuthor = prevAuthor != content.authorId
            val isLastMessageByAuthor = nextAuthor != content.authorId

            item {
                Message(
                    onAuthorClick = onAuthorClick,
                    message = content,
                    isUserMe = content.authorId == userId,
                    isFirstMessageByAuthor = isFirstMessageByAuthor,
                    isLastMessageByAuthor = isLastMessageByAuthor,
                    onImageClick = onImageClick,
                    onVideoClick = onVideoClick,
                    isGroupChat = isGroupChat
                )
            }
        }
    }
}

/**
 * author image 수정해야함!!
 */
@Composable
fun Message(
    onAuthorClick: (Long) -> Unit,
    message: Message,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean,
    onImageClick: (String) -> Unit,
    onVideoClick: (String) -> Unit,
    isGroupChat: Boolean
) {
    Row() {
        if (isLastMessageByAuthor) {
            ProfileImage(
                imageURL = message.authorName,
                profileSize = ProfileImageValue.MessageAuthorImageSize,
                modifier = Modifier.clickable { onAuthorClick(message.authorId) }
            )
        } else {
            Spacer(modifier = Modifier
                .size(ProfileImageValue.MessageAuthorImageSize.dp))
        }

        AuthorAndTextMessage(
            message = message,
            isUserMe = isUserMe,
            isFirstMessageByAuthor = isFirstMessageByAuthor,
            isLastMessageByAuthor = isLastMessageByAuthor,
            isGroupChat = isGroupChat,
            onImageClick = onImageClick,
            onVideoClick = onVideoClick
        )
    }
}

@Composable
fun AuthorAndTextMessage(
    message: Message,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean,
    isGroupChat: Boolean,
    onImageClick: (String) -> Unit,
    onVideoClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if(isFirstMessageByAuthor && isGroupChat) {
            Text(
                text = message.authorName
            )
        }
        ChatItemBubble(
            message = message,
            isUserMe = isUserMe,
            isFirstMessageByAuthor = isFirstMessageByAuthor,
            isLastMessageByAuthor = isLastMessageByAuthor,
            onImageClick = onImageClick,
            onVideoClick = onVideoClick
        )
    }


}

@Composable
fun ChatItemBubble(
    message: Message,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean,
    onImageClick: (String) -> Unit,
    onVideoClick: (String) -> Unit
) {
    val backgroundColor = if (isUserMe) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.secondary
    }

    val chatBubbleShape = if(isFirstMessageByAuthor && isLastMessageByAuthor) {
        ChatItemBubbleValue.defaultBubbleShape
    } else if (isFirstMessageByAuthor) (
        ChatItemBubbleValue.firstBubbleShape
    ) else if (isLastMessageByAuthor) {
        ChatItemBubbleValue.lastBubbleShape
    } else {
        ChatItemBubbleValue.betweenBubbleShape
    }

    Surface(
        color = backgroundColor,
        shape = chatBubbleShape
    ) {
        when(message.type) {
            MessageType.TEXT -> {
                ClickableMessage(
                    message = message,
                    isUserMe = isUserMe
                )
            }
            MessageType.IMAGE -> {
                AsyncImage(
                    modifier = Modifier.clickable { onImageClick(message.content) },
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(message.content)
                        .crossfade(true)
                        .build(),
                    contentDescription = "profile Image",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.blank_profile)
                )
            }
            MessageType.VIDEO -> {
                /**
                 * Todo
                 */
            }
        }
    }

}


@Composable
fun ClickableMessage(
    message: Message,
    isUserMe: Boolean
) {
    val uriHandler = LocalUriHandler.current

    val styleMessage = messageFormatter(
        text = message.content,
        isUserMe = isUserMe
    )

    ClickableText(
        text = styleMessage,
        onClick = {
            styleMessage
                .getStringAnnotations(start = it, end = it)
                .firstOrNull()
                ?.let {
                    if(it.tag == SymbolAnnotationType.LINK.name) {
                        uriHandler.openUri(it.item)
                    } else Unit
                }
        }
    )
}
