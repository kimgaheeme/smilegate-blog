package com.smilegateblog.smilegateteamprojecttest.ui.component

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smilegateblog.smilegateteamprojecttest.ui.theme.SmilegateTeamProjectTestTheme
import com.smilegateblog.smilegateteamprojecttest.ui.util.KeyLine

object PeopleItemValue {
    val ItemHeight = 56.dp
    const val ProfileSize = 56
    val CheckCircleSize = 14.dp
}

@Composable
fun PeopleWithTwoBtnItem(
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    leftBtnContent: String,
    rightBtnContent: String,
    imageURL: String,
    nickname: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        PeopleItem(imageURL = imageURL, nickname = nickname)
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            SmgButton(
                onClick = onLeftClick,
                content = leftBtnContent,
                modifier = Modifier
                    .size(
                        width = ButtonValue.SmallButtonWidth,
                        height = ButtonValue.SmallButtonHeight
                    )
            )

            SmgButton(
                onClick = onRightClick,
                content = rightBtnContent,
                modifier = Modifier
                    .size(
                        width = ButtonValue.SmallButtonWidth,
                        height = ButtonValue.SmallButtonHeight
                    ),
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onSecondary
            )
        }
    }
}


@Composable
fun PeopleWithSingleBtnItem(
    onClick: () -> Unit = {},
    btnContent: String = "수락",
    imageURL: String = "",
    nickname: String = "nickname",
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        PeopleItem(imageURL = imageURL, nickname = nickname)
        SmgButton(
            onClick = onClick,
            content = btnContent,
            modifier = Modifier
                .padding(KeyLine)
                .align(Alignment.CenterEnd)
                .size(
                    width = ButtonValue.SmallButtonWidth,
                    height = ButtonValue.SmallButtonHeight
                )
        )
    }
}


@Composable
fun PeopleWithCheckItem(
    onClick: () -> Unit,
    imageURL: String,
    nickname: String,
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onBtnColor: Color = MaterialTheme.colors.background,
    btnColor: Color = MaterialTheme.colors.primary
) {

    Box(
        modifier = modifier
            .clickable {
                onClick()
            }
    ) {
        PeopleItem(imageURL, nickname)

        if(isChecked){
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .padding(horizontal = KeyLine)
                    .align(Alignment.CenterEnd)
                    .size(PeopleItemValue.CheckCircleSize),
                color = btnColor
            ) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null,
                    tint = onBtnColor
                )
            }
        } else {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .padding(horizontal = KeyLine)
                    .align(Alignment.CenterEnd)
                    .size(PeopleItemValue.CheckCircleSize)
                    .border(2.dp, color = MaterialTheme.colors.secondary, shape = CircleShape),
            ){}
        }

    }
}

@Composable
fun PeopleItem(
    imageURL: String = "",
    nickname: String = "nickname",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(PeopleItemValue.ItemHeight),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProfileImage(
            imageURL = imageURL,
            profileSize = PeopleItemValue.ProfileSize
        )

        Text(
            text = nickname,
            fontSize = 17.sp
        )
    }
}
