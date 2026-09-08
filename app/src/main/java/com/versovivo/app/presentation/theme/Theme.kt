package com.versovivo.app.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Gold = Color(0xFFD4AF37)
private val GoldDark = Color(0xFFB8860B)
private val Ivory = Color(0xFFFFFDF7)
private val IvoryDark = Color(0xFF111111)
private val GoldSoft = Color(0xFFF9E7A7)

private val LightColorScheme = lightColorScheme(
    primary = Gold,
    onPrimary = Color(0xFF1B1B1B),
    secondary = GoldDark,
    tertiary = GoldSoft,
    background = Ivory,
    onBackground = Color(0xFF171717),
    surface = Color(0xFFFFF9F0),
    onSurface = Color(0xFF1B1B1B),
    primaryContainer = Color(0xFFFFF1C8),
    onPrimaryContainer = Color(0xFF1B1B1B)
)

private val DarkColorScheme = darkColorScheme(
    primary = Gold,
    onPrimary = Color(0xFF1B1B1B),
    secondary = GoldSoft,
    tertiary = GoldDark,
    background = IvoryDark,
    onBackground = Color(0xFFF5F5F5),
    surface = Color(0xFF1C1C1C),
    onSurface = Color(0xFFF5F5F5),
    primaryContainer = Color(0xFF2A2A1A),
    onPrimaryContainer = Color(0xFFF4E9B3)
)

@Composable
fun VersoVivoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
