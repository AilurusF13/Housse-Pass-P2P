package fr.ailurus.housepassp2p.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.ailurus.housepassp2p.ui.screens.VaultScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: AppDestinations,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        AppDestinations.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    AppDestinations.VAULT -> VaultScreen() // TODO implement VaultScreen
                    AppDestinations.GROUPS -> {} // TODO implement group page
                    AppDestinations.SETTINGS -> {} // TODO implement setting page
                }
            }
        }
    }
}

enum class AppDestinations(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    VAULT("vault", "Vault", Icons.Default.Home),
    GROUPS("groups", "Groups", Icons.Default.Groups),
    SETTINGS("settings", "Settings", Icons.Default.Settings);
}