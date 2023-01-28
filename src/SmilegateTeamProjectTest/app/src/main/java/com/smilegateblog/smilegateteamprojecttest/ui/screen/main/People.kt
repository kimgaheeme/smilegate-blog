package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.smilegateblog.smilegateteamprojecttest.R
import com.smilegateblog.smilegateteamprojecttest.model.People
import com.smilegateblog.smilegateteamprojecttest.ui.component.PeopleItem
import com.smilegateblog.smilegateteamprojecttest.ui.component.PeopleWithTwoBtnItem
import com.smilegateblog.smilegateteamprojecttest.ui.component.SubTitle
import com.smilegateblog.smilegateteamprojecttest.ui.component.TopBarWithProfile
import com.smilegateblog.smilegateteamprojecttest.ui.util.KeyLine
import com.smilegateblog.smilegateteamprojecttest.ui.viewmodel.main.PeopleViewModel

object PeopleScreenValue {
    var SpacerBetweenFriends = 12.dp
    var SpacerBetweenTitleAndRequest = 12.dp
}


@Composable
fun People(
    navigateToAddPeople: () -> Unit
) {
    val viewModel = hiltViewModel<PeopleViewModel>()
    val state by viewModel.peopleState.collectAsState()
    val friends = state.friends
    val requests = state.requests

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = KeyLine)
    ) {
        TopBarWithProfile(
            onClick = { navigateToAddPeople() },
            profileImage = "",
            content = stringResource(id = R.string.people_title),
            icon = ImageVector.vectorResource(id = R.drawable.ic_person_add)
        )
        
        LazyColumn() {
            if(requests.isNotEmpty()) {
                item {
                    SubTitle(
                        content = stringResource(id = R.string.people_friend_request_subtitle)
                    )
                    Spacer(modifier = Modifier.size(PeopleScreenValue.SpacerBetweenTitleAndRequest))
                }
            }

            items(requests) { request ->
                PeopleWithTwoBtnItem(
                    onLeftClick = { /*TODO*/ },
                    onRightClick = { /*TODO*/ },
                    leftBtnContent = stringResource(id = R.string.people_accept_btn),
                    rightBtnContent = stringResource(id = R.string.people_deny_btn),
                    imageURL = request.profileImg,
                    nickname = request.nickname
                )
                Spacer(modifier = Modifier.size(PeopleScreenValue.SpacerBetweenFriends))
            }

            item {
                SubTitle(
                    content = stringResource(id = R.string.people_friend_subtitle),
                    modifier = Modifier.padding(vertical = 9.dp)
                )
            }

            items(friends) { friend ->
                PeopleItem(
                    imageURL = friend.profileImg,
                    nickname = friend.nickname
                )

                Spacer(modifier = Modifier.size(PeopleScreenValue.SpacerBetweenFriends))
            }
        }
    }
}
