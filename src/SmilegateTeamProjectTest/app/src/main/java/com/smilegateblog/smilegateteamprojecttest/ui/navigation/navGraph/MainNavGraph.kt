package com.smilegateblog.smilegateteamprojecttest.ui.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.smilegateblog.smilegateteamprojecttest.ui.navigation.BottomBarDestinations
import com.smilegateblog.smilegateteamprojecttest.ui.navigation.LoginDestinations
import com.smilegateblog.smilegateteamprojecttest.ui.navigation.MainDestinations
import com.smilegateblog.smilegateteamprojecttest.ui.navigation.MainNavigationAction
import com.smilegateblog.smilegateteamprojecttest.ui.screen.Login
import com.smilegateblog.smilegateteamprojecttest.ui.screen.initial.SignUp
import com.smilegateblog.smilegateteamprojecttest.ui.screen.main.Chats
import com.smilegateblog.smilegateteamprojecttest.ui.screen.main.People
import com.smilegateblog.smilegateteamprojecttest.ui.screen.main.Setting

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.MAIN_ROUTE,
    navigationAction: MainNavigationAction
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = MainDestinations.MAIN_ROUTE,
            startDestination = BottomBarDestinations.CHATS_ROUTE
        ) {
            bottomNavGraph(navigationAction)
        }
    }
}

private fun NavGraphBuilder.bottomNavGraph(
    navigationAction: MainNavigationAction
) {
    composable(BottomBarDestinations.CHATS_ROUTE) { from ->
        Chats()
    }

    composable(BottomBarDestinations.PEOPLE_ROUTE) { from ->
        People()
    }

    composable(BottomBarDestinations.SETTING_ROUTE) { from ->
        Setting()
    }

}
