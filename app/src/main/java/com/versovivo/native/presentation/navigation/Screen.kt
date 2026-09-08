package com.versovivo.native.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Book
import androidx.compose.runtime.Composable

sealed class Screen(
    val route: String,
    val title: String,
    val icon: @Composable () -> Unit
) {
    data object Home : Screen("home", "Home", { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = "Home") })
    data object Devotional : Screen("devotional", "Devocional", { androidx.compose.material3.Icon(Icons.Default.Book, contentDescription = "Devocional") })
    data object Music : Screen("music", "Músicas", { androidx.compose.material3.Icon(Icons.Default.MusicNote, contentDescription = "Músicas") })
    data object User : Screen("user", "Perfil", { androidx.compose.material3.Icon(Icons.Default.Person, contentDescription = "Perfil") })
    data object Settings : Screen("settings", "Config", { androidx.compose.material3.Icon(Icons.Default.Settings, contentDescription = "Configuração") })
}
