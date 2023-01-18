package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun People(
    navigateToAddPeople: () -> Unit
) {
    Column() {
        Text("People")
        Button(onClick = navigateToAddPeople) {
            Text("to Add People")
        }
    }
}