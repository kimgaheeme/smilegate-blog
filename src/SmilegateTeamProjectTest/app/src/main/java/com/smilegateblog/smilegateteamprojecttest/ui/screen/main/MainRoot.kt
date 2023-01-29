package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.smilegateblog.smilegateteamprojecttest.ui.component.SmgBottomBar
import com.smilegateblog.smilegateteamprojecttest.ui.navigation.MainDestinations
import com.smilegateblog.smilegateteamprojecttest.ui.navigation.navGraph.MainNavGraph
import com.smilegateblog.smilegateteamprojecttest.ui.state.rememberAppState
import com.smilegateblog.smilegateteamprojecttest.ui.theme.SmilegateTeamProjectTestTheme

@Composable
fun MainRootScreen() {
    SmilegateTeamProjectTestTheme {
        val appState = rememberAppState()
        val navigationAction = appState.navigationAction

        Scaffold(
            scaffoldState = appState.scaffoldState,
            bottomBar = {
                if(appState.shouldShowBottomBar) {
                    SmgBottomBar(
                        tabs = appState.bottomBarTabs,
                        currentRoute = appState.currentRoute,
                        navigateToRoute = navigationAction.navigateToBottomRoute
                    )
                }
            }
        ) { innerPadding ->
            MainNavGraph(
                navController = appState.navController,
                startDestination = MainDestinations.MAIN_ROUTE,
                navigationAction = navigationAction
            )
        }

    }
}