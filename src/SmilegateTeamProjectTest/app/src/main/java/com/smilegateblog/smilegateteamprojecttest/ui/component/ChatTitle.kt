package com.smilegateblog.smilegateteamprojecttest.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smilegateblog.smilegateteamprojecttest.ui.theme.SmilegateTeamProjectTestTheme

@Composable
fun ChatTitle(
    chatroomTitle: String = "",
    images: List<String?>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProfileImages(
            images = images,
            profileSize = ProfileImageValue.ChatTitleImageSize
        )

        Text(
            text = chatroomTitle,
            fontSize = 17.sp
        )
    }
}

@Composable
@Preview
fun PreviewChatTitle() {
    SmilegateTeamProjectTestTheme() {
        ChatTitle(images = listOf(""), chatroomTitle = "fkawnekfanwle")
    }
}