package com.smilegateblog.smilegateteamprojecttest.ui.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smilegateblog.smilegateteamprojecttest.ui.navigation.*
import com.smilegateblog.smilegateteamprojecttest.ui.screen.Login
import com.smilegateblog.smilegateteamprojecttest.ui.screen.initial.SignUp
import com.smilegateblog.smilegateteamprojecttest.ui.screen.main.*

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

            chatGraph(navigationAction)
        }
    }
}

private fun NavGraphBuilder.bottomNavGraph(
    navigationAction: MainNavigationAction
) {
    composable(BottomBarDestinations.CHATS_ROUTE) { from ->
        Chats(
            navigateToChat = { chatId -> navigationAction.navigateToChat(chatId, from)},
            navigateToAddChat = { navigationAction.navigateToAddChat() }
        )
    }

    navigation(
        route = MainDestinations.PEOPLE_GRAPH_ROUTE,
        startDestination = BottomBarDestinations.PEOPLE_ROUTE
    ) {
        peopleGraph(navigationAction)
    }

    composable(BottomBarDestinations.SETTING_ROUTE) { from ->
        Setting()
    }
}

private fun NavGraphBuilder.chatGraph(
    navigationAction: MainNavigationAction
) {
    composable(
        route = MainDestinations.CHAT_ROUTE
    ) { from ->
        Chat(
            navigateToChatInfo = { chatId -> navigationAction.navigateToChatInfo(chatId, from)}
        )
    }

    composable(
        route = "${MainDestinations.CHAT_ROUTE}/{${DestinationID.CHAT_ID}}"
    ) { from ->
        Chat(
            navigateToChatInfo = { chatId -> navigationAction.navigateToChatInfo(chatId, from)}
        )
    }

    composable(
        route = "${MainDestinations.CHAT_INFO_ROUTE}/{${DestinationID.CHAT_ID}}"
    ) { from ->
        ChatInfo(
            navigateToAddMember = { chatId -> navigationAction.navigateToAddMember(chatId, from)}
        )
    }

    composable(
        route = "${MainDestinations.ADD_CHAT_MEMBER_ROUTE}/{${DestinationID.CHAT_ID}}"
    ) { from ->
        AddChatMember(
            navigateToNewChat = { navigationAction.navigateToNewChat() },
            navigateToUpdateGroupChat = { chatId -> navigationAction.navigateToUpdateGroupChat(chatId, from)}
        )
    }

    composable(
        route = MainDestinations.ADD_CHAT_ROUTE
    ) { from ->
        AddChat(
            navigateToNewChat = { navigationAction.navigateToNewChat() }
        )
    }
}

private fun NavGraphBuilder.peopleGraph(
    navigationAction: MainNavigationAction
) {
    composable(BottomBarDestinations.PEOPLE_ROUTE) { from ->
        People(
            navigateToAddPeople = navigationAction.navigateToAddPeople
        )
    }
    composable(MainDestinations.ADD_PEOPLE_ROUTE) { from ->
        AddPeople()
    }
}
