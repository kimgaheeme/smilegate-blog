package com.smilegateblog.smilegateteamprojecttest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smilegateblog.smilegateteamprojecttest.ui.screen.LoginScreen
import com.smilegateblog.smilegateteamprojecttest.ui.screen.initial.SignUpScreen

@Composable
fun LoginNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = LoginDestinations.LOGIN_ROUTE,
    navigationAction: LoginNavigationAction,
    navigateToMain: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(LoginDestinations.LOGIN_ROUTE) {
            LoginScreen(
                navigateToSignUp = navigationAction.navigateToSignUp,
                navigateToMain = navigateToMain
            )
        }

        composable(LoginDestinations.SIGN_UP_ROUTE) {
            SignUpScreen(navigationAction.upPress)
        }
    }
}