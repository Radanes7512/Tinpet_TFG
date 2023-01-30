package com.example.tinpet.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    background = GreyBG, // FONDO PANTALLA
    onBackground = White, // TEXTO SOBRE FONDO

    primaryVariant = GreyNavBar, // BARRA NAVEGACIÓN SUPERIOR

    surface = GreyNavBar, // BARRA NAVEGACIÓN INFERIOR
    onPrimary = White, // ICONOS NAVEGACIÓN INFERIOR

    primary = GreyNavBar, // COLOR PRINCIPAL
    secondary = Green10, // COLOR SECUNDARIO
    secondaryVariant = Yellow10,

    onSurface = Color.White // NO ESPECIFICADO
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    background = White, // FONDO PANTALLA
    onBackground = Black, // TEXTO SOBRE FONDO

    primaryVariant = YellowNavBar, // BARRA NAVEGACIÓN SUPERIOR

    surface = YellowNavBar, // BARRA NAVEGACIÓN INFERIOR
    onPrimary = Black, // ICONOS NAVEGACIÓN INFERIOR

    primary = Yellow10, // COLOR PRINCIPAL
    secondary = Green10, // COLOR SECUNDARIO
    secondaryVariant = Yellow10,

    onSurface = Color.Black // NO ESPECIFICADO
 )

@Composable
fun TinPetTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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