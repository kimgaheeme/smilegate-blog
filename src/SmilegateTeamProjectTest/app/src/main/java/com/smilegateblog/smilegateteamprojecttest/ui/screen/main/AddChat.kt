package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun AddChat(
    navigateToNewChat: () -> Unit,
) {
    Column() {
        Text("Add Chat")
        Button(onClick = navigateToNewChat) {
            Text("To New Chat")
        }
    }
}