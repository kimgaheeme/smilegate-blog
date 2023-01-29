package com.smilegateblog.smilegateteamprojecttest.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smilegateblog.smilegateteamprojecttest.ui.theme.SmilegateTeamProjectTestTheme


object ButtonValue {
    val ButtonShape = RoundedCornerShape(12.dp)
    val LargeButtonHeight = 45.dp

    val MediumButtonHeight = 36.dp
    val MediumButtonWidth = 226.dp

    val SmallButtonHeight = 36.dp
    val SmallButtonWidth = 88.dp
}

@Composable
fun SmgButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isClicked: Boolean = false,
    shape: Shape = ButtonValue.ButtonShape,
    border: BorderStroke? = null,
    content: String = "",
    contentColor: Color = MaterialTheme.colors.onPrimary,
    disabledContentColor: Color = MaterialTheme.colors.onSecondary,
    backgroundColor: Color = MaterialTheme.colors.primary,
    disabledColor: Color = MaterialTheme.colors.secondary,
    clickedContent: String = ""
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = disabledColor,
            backgroundColor = if(isClicked) disabledColor else backgroundColor,
            disabledContentColor = disabledContentColor,
            contentColor = if(isClicked) disabledContentColor else contentColor
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
        ),
        border = border,
        shape = shape,
        modifier = modifier,
        enabled = enabled
    ) {
        Text(
            text = if(isClicked) clickedContent else content,
            fontSize = 14.sp
        )
    }
}


@Composable
@Preview
fun previewButton() {
    SmilegateTeamProjectTestTheme() {
        Column() {
            SmgButton(onClick = { /*TODO*/ }, content = "Text")
            SmgButton(onClick = { /*TODO*/ }, content = "Text", enabled = false)
            SmgButton(onClick = { /*TODO*/ }, content = "Text", isClicked = true)
        }
        
    }
}