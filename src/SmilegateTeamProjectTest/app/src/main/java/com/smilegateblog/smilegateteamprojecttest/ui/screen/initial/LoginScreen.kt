package com.smilegateblog.smilegateteamprojecttest.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.smilegateblog.smilegateteamprojecttest.ui.navigation.LoginNavigationAction
import com.smilegateblog.smilegateteamprojecttest.ui.theme.SmilegateTeamProjectTestTheme

@Composable
fun LoginScreen(
    navigateToSignUp:() -> Unit = {},
    navigateToMain:() -> Unit = {}
) {
    Column() {
        Button(onClick = navigateToSignUp) {
            Text("현재는 Login 누르면 SignUp")
        }
        Button(onClick = navigateToMain) {
            Text("현재는 Login 누르면 Main")
        }
    }
}