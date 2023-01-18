package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ChatInfo(
    navigateToAddMember: (Int) -> Unit
){
    Column() {
        Text("ChatInfo")
        Button(onClick = { navigateToAddMember(1) }) {
            Text("go to AddMember ")
        }
    }
}