package com.versovivo.app.presentation.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.versovivo.app.domain.model.BibleVerse
import com.versovivo.app.presentation.screen.*
import com.versovivo.app.presentation.viewmodel.BibleViewModel
import com.versovivo.app.presentation.viewmodel.DevotionalViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    darkMode: Boolean,
    onToggleDarkMode: () -> Unit
) {
    // ViewModels shared or scoped as needed
    val bibleViewModel: BibleViewModel = viewModel()
    val devotionalViewModel: DevotionalViewModel = viewModel()
    
    // Temporary storage for verses being turned into a devotional
    var pendingVerses by remember { mutableStateOf<List<BibleVerse>>(emptyList()) }

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = modifier
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onGoToBible = { navController.navigate(Screen.Bible.route) },
                onGoToMusic = { navController.navigate(Screen.Music.route) },
                onGoToUser = { navController.navigate(Screen.User.route) },
                onGoToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        composable(Screen.Bible.route) {
            BibleScreen(
                viewModel = bibleViewModel,
                onCreateDevotional = { verses ->
                    pendingVerses = verses
                    navController.navigate(Screen.CreateDevotional.route)
                }
            )
        }
        composable(Screen.CreateDevotional.route) {
            CreateDevotionalScreen(
                selectedVerses = pendingVerses,
                onSave = { title, theme, date, reminder ->
                    devotionalViewModel.addDevotional(title, theme, date, pendingVerses, "", reminder)
                    navController.navigate(Screen.MyDevotionals.route) {
                        popUpTo(Screen.Bible.route) // Go back to Bible as root of this flow
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Music.route) {
            MusicScreen()
        }
        composable(Screen.User.route) {
            UserScreen(
                onGoToMyDevotionals = { navController.navigate(Screen.MyDevotionals.route) }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                darkMode = darkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }
        composable(Screen.MyDevotionals.route) {
            MyDevotionalsScreen(
                viewModel = devotionalViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
