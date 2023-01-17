package com.smilegateblog.smilegateteamprojecttest.ui.screen.initial

import android.content.Intent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.smilegateblog.smilegateteamprojecttest.ui.navigation.LoginNavGraph
import com.smilegateblog.smilegateteamprojecttest.ui.navigation.LoginNavigationAction
import com.smilegateblog.smilegateteamprojecttest.ui.screen.main.MainActivity
import com.smilegateblog.smilegateteamprojecttest.ui.theme.SmilegateTeamProjectTestTheme

@Composable
fun Login(
    navigateToMain:() -> Unit
) {
    SmilegateTeamProjectTestTheme {
        val scaffoldState = rememberScaffoldState()
        val navController = rememberNavController()
        val navigationAction = LoginNavigationAction(navController)

        Scaffold(
            scaffoldState = scaffoldState
        ) {
            LoginNavGraph(
                navController = navController,
                navigationAction = navigationAction,
                navigateToMain = navigateToMain
            )
        }
    }
}