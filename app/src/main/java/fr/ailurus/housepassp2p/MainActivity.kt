package fr.ailurus.housepassp2p

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.lifecycleScope
import fr.ailurus.housepassp2p.data.AppDatabase
import fr.ailurus.housepassp2p.ui.screens.LoginScreen
import fr.ailurus.housepassp2p.ui.screens.StartupScreen
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme
import fr.ailurus.housepassp2p.ui.viewmodels.LoginViewModel
import fr.ailurus.housepassp2p.ui.viewmodels.SetupViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        For testing purposes
        lifecycleScope.launch(Dispatchers.IO) {
            val passwordBytes = AppDatabase.getDatabase(context = applicationContext).entryDao().getPassword(1)
            Log.d("FETCH TEST", "A password = ${passwordBytes?.decodeToString() ?: "no entry in database"}")
        }

        setContent {
            HousePassP2PTheme {
                HousePassP2PApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun HousePassP2PApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.LOGIN) }
    val loginViewModel = LoginViewModel()
    val setupViewModel = SetupViewModel()

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label,
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it },
                )
            }
        },
    ) {
        when (currentDestination) {
            AppDestinations.LOGIN -> LoginScreen(viewModel = loginViewModel)
            AppDestinations.STARTUP -> StartupScreen(viewModel = setupViewModel)
            AppDestinations.PROFILE -> LoginScreen(viewModel = loginViewModel)
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    LOGIN("Login", Icons.Default.Lock),
    STARTUP("Startup", Icons.Default.Create),
    PROFILE("Profile", Icons.Default.AccountBox),
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HousePassP2PTheme {
        HousePassP2PApp()
    }
}
