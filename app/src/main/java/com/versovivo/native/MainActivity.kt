package com.versovivo.native

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.versovivo.native.presentation.navigation.AppNavHost
import com.versovivo.native.presentation.navigation.Screen
import com.versovivo.native.presentation.theme.VersoVivoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            var darkMode by rememberSaveable { mutableStateOf(false) }

            VersoVivoTheme(darkTheme = darkMode) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Verso Vivo") },
                            actions = {
                                IconButton(onClick = { darkMode = !darkMode }) {
                                    Icon(
                                        imageVector = if (darkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                                        contentDescription = "Alternar tema"
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        NavigationBar {
                            listOf(
                                Screen.Home,
                                Screen.Devotional,
                                Screen.Music,
                                Screen.User,
                                Screen.Settings
                            ).forEach { screen ->
                                NavigationBarItem(
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = { screen.icon() },
                                    label = { Text(screen.title) }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        darkMode = darkMode,
                        onToggleDarkMode = { darkMode = !darkMode }
                    )
                }
            }
        }
    }
}
