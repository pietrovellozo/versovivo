package com.versovivo.app.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

sealed class Screen(
    val route: String,
    val title: String = "",
    val icon: @Composable () -> Unit = {}
) {
    data object Login : Screen("login", "Login", { androidx.compose.material3.Icon(Icons.Default.Login, contentDescription = "Login") })
    data object Home : Screen("home", "Home", { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = "Home") })
    data object Bible : Screen("bible", "Bíblia", { androidx.compose.material3.Icon(Icons.Default.AutoStories, contentDescription = "Bíblia") })
    data object Music : Screen("music", "Músicas", { androidx.compose.material3.Icon(Icons.Default.MusicNote, contentDescription = "Músicas") })
    data object User : Screen("user", "Perfil", { androidx.compose.material3.Icon(Icons.Default.Person, contentDescription = "Perfil") })
    data object Settings : Screen("settings", "Config", { androidx.compose.material3.Icon(Icons.Default.Settings, contentDescription = "Configuração") })
    data object MyDevotionals : Screen("my_devotionals", "Meus Devocionais", { androidx.compose.material3.Icon(Icons.Default.EditNote, contentDescription = "Meus Devocionais") })
    data object CreateDevotional : Screen("create_devotional", "Novo Devocional", { androidx.compose.material3.Icon(Icons.Default.Add, contentDescription = "Novo Devocional") })
}
