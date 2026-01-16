package fr.ailurus.housepassp2p.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fr.ailurus.housepassp2p.ui.navigation.AppDestinations
import fr.ailurus.housepassp2p.ui.navigation.AppNavHost
import fr.ailurus.housepassp2p.ui.screens.LoginScreen
import fr.ailurus.housepassp2p.ui.screens.StartupScreen

@Composable
fun HousePassP2PApp(hasExistingDb: Boolean) {

    var isAppUnlocked by rememberSaveable { mutableStateOf(false) }

    if (isAppUnlocked) {
        MainAppContent()
    } else {
        if (hasExistingDb) {
            LoginScreen(
                onAuthSuccess = { isAppUnlocked = true }
            )
        } else {
            StartupScreen(
                onAuthSuccess = { isAppUnlocked = true }
            )
        }
    }
}

@Composable
fun MainAppContent() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { dest ->
                item(
                    icon = { Icon(dest.icon, contentDescription = dest.label) },
                    label = { Text(dest.label) },
                    selected = currentDestination?.route == dest.route,
                    onClick = {
                        navController.navigate(dest.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        },
    ) {
        AppNavHost(navController, startDestination = AppDestinations.VAULT)
    }
}