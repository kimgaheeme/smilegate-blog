package com.smilegateblog.smilegateteamprojecttest.ui.screen.initial

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SignUpScreen(onBackClick: () -> Unit) {
    Button(onClick = onBackClick) {
        Text("현재는 SignUpScreen")
    }
}
