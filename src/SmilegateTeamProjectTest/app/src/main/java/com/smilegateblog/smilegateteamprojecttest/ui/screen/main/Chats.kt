package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Chats(
    navigateToChat: (Int) -> Unit
) {
    Column() {
        Text("Chats")
        Button(onClick = { navigateToChat(1) }) {
            Text("go to chat ")
        }
    }
}