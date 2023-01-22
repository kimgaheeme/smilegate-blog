package com.smilegateblog.smilegateteamprojecttest.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Orange200,
    primaryVariant = Purple700,
    secondary = Gray200,
    background = Gray100,
    onBackground = Gray800,
    onPrimary = Gray100,
    onSecondary = Gray800
)

private val LightColorPalette = lightColors(
    primary = Orange200,
    primaryVariant = Purple700,
    secondary = Gray200,
    background = Gray100,
    onBackground = Gray800,
    onPrimary = Gray100,
    onSecondary = Gray800

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SmilegateTeamProjectTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}