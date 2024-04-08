package com.clement.newsapp.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Orange,
    primaryVariant = OrangeDark,
    secondary = Orange,
    background = Color.White,
    onBackground = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = Orange,
    primaryVariant = OrangeDark,
    secondary = Orange,
    background = Color.White,
    onBackground = Color.Black,

    /* Other default colors to override
    onBackground = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun NewsAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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