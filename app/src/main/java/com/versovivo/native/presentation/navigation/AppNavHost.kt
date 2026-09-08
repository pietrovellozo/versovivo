package com.versovivo.native.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.versovivo.native.di.AppContainer
import com.versovivo.native.presentation.screen.DevotionalScreen
import com.versovivo.native.presentation.screen.HomeScreen
import com.versovivo.native.presentation.screen.MusicScreen
import com.versovivo.native.presentation.screen.SettingsScreen
import com.versovivo.native.presentation.screen.UserScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    darkMode: Boolean,
    onToggleDarkMode: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onGoToDevotional = { navController.navigate(Screen.Devotional.route) },
                onGoToMusic = { navController.navigate(Screen.Music.route) },
                onGoToUser = { navController.navigate(Screen.User.route) },
                onGoToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        composable(Screen.Devotional.route) {
            DevotionalScreen(
                factory = AppContainer.provideVerseViewModelFactory()
            )
        }
        composable(Screen.Music.route) {
            MusicScreen()
        }
        composable(Screen.User.route) {
            UserScreen()
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                darkMode = darkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }
    }
}
