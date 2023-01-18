package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Chat(
    navigateToChatInfo: (Int) -> Unit
) {
    Column() {
        Text("Chat")
        Button(onClick = { navigateToChatInfo(1) }) {
            Text("go to chatInfo ")
        }
    }
}