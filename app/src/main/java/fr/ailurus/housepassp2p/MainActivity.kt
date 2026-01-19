package fr.ailurus.housepassp2p

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.ui.MainApp
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val dbName = applicationContext.getString(R.string.database_name)
            val dbExists = applicationContext.getDatabasePath(dbName).exists()

            HousePassP2PTheme {
                MainApp(hasExistingDb = dbExists)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HousePassP2PTheme {
        MainApp(true)
    }
}
