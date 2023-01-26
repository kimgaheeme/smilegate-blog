package com.smilegateblog.smilegateteamprojecttest.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.smilegateblog.smilegateteamprojecttest.ui.theme.Gray400

@Composable
fun SubTitle(
    content: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = content,
        fontSize = 13.sp,
        color = Gray400,
        modifier = modifier
    )
}