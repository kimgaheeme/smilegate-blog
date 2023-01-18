package com.smilegateblog.smilegateteamprojecttest.ui.navigation

import androidx.navigation.NavController
import com.smilegateblog.smilegateteamprojecttest.ui.component.BottomBarTabs

object LoginDestinations {
    const val LOGIN_ROUTE = "login"
    const val SIGN_UP_ROUTE = "signup"
}

object BottomBarDestinations {
    const val CHATS_ROUTE = "chats"
    const val PEOPLE_ROUTE = "people"
    const val SETTING_ROUTE = "setting"
}

object MainDestinations {
    const val MAIN_ROUTE = "main"
}

class LoginNavigationAction(navController: NavController) {
    val upPress:() -> Unit = {
        navController.navigateUp()
    }

    val navigateToSignUp:() -> Unit = {
        navController.navigate(LoginDestinations.SIGN_UP_ROUTE)
    }
}



class MainNavigationAction(val navController: NavController) {
    private val currentRoute: String?
        get() = navController.currentDestination?.route

    val upPress:() -> Unit = {
        navController.navigateUp()
    }

    val navigateToBottomRoute: (String) -> Unit = { route ->
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(BottomBarTabs.Chats.route) {
                    saveState = true
                }
            }
        }
    }
}