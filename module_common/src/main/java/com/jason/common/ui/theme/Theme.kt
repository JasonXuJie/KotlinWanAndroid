package com.jason.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


val DarkColorPalette = darkColors(
    primary = ColorPrimary,
    primaryVariant = ColorPrimary,
    secondary = ColorPrimary
)


val LightColorPalette = lightColors(
    primary = ColorPrimary,
    primaryVariant = ColorPrimary,
    secondary = ColorPrimary

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
fun KotlinWanAndroidTheme(
    darkTheme:Boolean = isSystemInDarkTheme(),
    content: @Composable ()->Unit
){
    val colors  = if (darkTheme){
        DarkColorPalette
    }else{
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}