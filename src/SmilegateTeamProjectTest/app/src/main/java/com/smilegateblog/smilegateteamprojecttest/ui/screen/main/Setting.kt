package com.smilegateblog.smilegateteamprojecttest.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smilegateblog.smilegateteamprojecttest.R
import com.smilegateblog.smilegateteamprojecttest.ui.component.ProfileImage
import com.smilegateblog.smilegateteamprojecttest.ui.component.SubTitle
import com.smilegateblog.smilegateteamprojecttest.ui.theme.Gray400
import com.smilegateblog.smilegateteamprojecttest.ui.theme.SmilegateTeamProjectTestTheme
import com.smilegateblog.smilegateteamprojecttest.ui.util.KeyLine

object SettingValue{
    val SettingComponentHeight = 60.dp
    val SettingIconSize = 36.dp
    const val ProfileImageSize = 100
    val ProfileImageTopPadding = 118.dp
    val ProfileImageBottomPadding = 55.dp
}

@Composable
fun Setting() {

    var state1 by remember { mutableStateOf(false) }
    var state2 by remember { mutableStateOf(false) }
    var state3 by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(horizontal = KeyLine)
    ) {
        ProfileImage(
            imageURL = "",
            profileSize = SettingValue.ProfileImageSize,
            modifier = Modifier
                .padding(top= SettingValue.ProfileImageTopPadding)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "userName",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            modifier = Modifier
                .padding(bottom= SettingValue.ProfileImageBottomPadding)
                .align(Alignment.CenterHorizontally)
        )
        
        SubTitle(
            content = stringResource(id = R.string.setting_system_subtitle))

        SettingComponentToggle(
            content = stringResource(id = R.string.setting_dark_mode_btn),
            image = ImageVector.vectorResource(id = R.drawable.ic_set_mode),
            onClick = { state1 = !state1 },
            isChecked = state1
        )

        SettingComponentToggle(
            content = stringResource(id = R.string.setting_alarm_btn),
            image = ImageVector.vectorResource(id = R.drawable.ic_set_bell),
            onClick = { state2 = !state2 },
            isChecked = state2
        )

        SettingComponentToggle(
            content = stringResource(id = R.string.setting_active_btn),
            image = ImageVector.vectorResource(id = R.drawable.ic_set_active),
            onClick = { state3 = !state3 },
            isChecked = state3
        )

        SubTitle(content = stringResource(id = R.string.setting_account_subtitle))

        SettingComponent(
            content = stringResource(id = R.string.setting_profile_btn),
            image = ImageVector.vectorResource(id = R.drawable.ic_set_active),
            onClick = { /*TODO*/ }
        )

        SettingComponent(
            content = stringResource(id = R.string.setting_logout_btn),
            image = ImageVector.vectorResource(id = R.drawable.ic_set_out),
            onClick = { /*TODO*/ }
        )

        SettingComponent(
            content = stringResource(id = R.string.setting_withdraw_btn),
            image = ImageVector.vectorResource(id = R.drawable.ic_set_out),
            onClick = { /*TODO*/ }
        )
    }
}

@Composable
fun SettingComponentToggle(
    content: String,
    image: ImageVector,
    onClick:() -> Unit,
    modifier: Modifier = Modifier,
    isChecked: Boolean
) {

    val checkedState = remember { mutableStateOf(isChecked) }
    Box() {
        SettingComponent(
            content = content,
            image = image,
            onClick = onClick,
            modifier = modifier,
            clickable = false
        )

        Switch(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onClick()
                              },
            modifier = Modifier.align(Alignment.CenterEnd),
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Gray400,
                uncheckedTrackColor = Gray400,
                checkedThumbColor = MaterialTheme.colors.primary,
                checkedTrackColor = MaterialTheme.colors.primary
            )
        )
    }
}

@Composable
fun SettingComponent(
    content: String ,
    image: ImageVector,
    onClick:() -> Unit,
    clickable: Boolean = true,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(SettingValue.SettingComponentHeight)
            .clickable(
                enabled = clickable
            ) { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = CircleShape,
            modifier = modifier.size(SettingValue.SettingIconSize)
        ) {
            Image(
                imageVector = image,
                contentDescription = null
            )
        }
        
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = content,
            fontSize = 16.sp
        )
    }
}
