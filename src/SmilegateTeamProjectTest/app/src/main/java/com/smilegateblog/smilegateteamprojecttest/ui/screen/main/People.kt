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
import com.smilegateblog.smilegateteamprojecttest.R
import com.smilegateblog.smilegateteamprojecttest.model.People
import com.smilegateblog.smilegateteamprojecttest.ui.component.PeopleItem
import com.smilegateblog.smilegateteamprojecttest.ui.component.PeopleWithTwoBtnItem
import com.smilegateblog.smilegateteamprojecttest.ui.component.SubTitle
import com.smilegateblog.smilegateteamprojecttest.ui.component.TopBarWithProfile
import com.smilegateblog.smilegateteamprojecttest.ui.util.KeyLine

object PeopleScreenValue {
    var SpacerBetweenFriends = 12.dp
    var SpacerBetweenTitleAndRequest = 12.dp
}


@Composable
fun People(
    navigateToAddPeople: () -> Unit
) {
    var friendsRequest by remember{ mutableStateOf(listOf<People>(
        People("nickname", "asdf", "asdf", "", 1),
        People("nickname", "asdf", "asdf", "", 1)
    )) }
    var friends by remember{ mutableStateOf(listOf<People>(
        People("nickname", "asdf", "asdf", "", 1),
        People("nickname", "asdf", "asdf", "", 1)
    )) }

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
            if(friendsRequest.isNotEmpty()) {
                item {
                    SubTitle(
                        content = stringResource(id = R.string.people_friend_request_subtitle)
                    )
                    Spacer(modifier = Modifier.size(PeopleScreenValue.SpacerBetweenTitleAndRequest))
                }
            }

            items(friendsRequest) { request ->
                PeopleWithTwoBtnItem(
                    onLeftClick = { /*TODO*/ },
                    onRightClick = { /*TODO*/ },
                    leftBtnContent = stringResource(id = R.string.people_accept_btn),
                    rightBtnContent = stringResource(id = R.string.people_deny_btn),
                    imageURL = request.profileImage,
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
                    imageURL = friend.profileImage,
                    nickname = friend.nickname
                )

                Spacer(modifier = Modifier.size(PeopleScreenValue.SpacerBetweenFriends))
            }
        }
    }
}
