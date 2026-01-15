package fr.ailurus.housepassp2p.ui.components.main

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.HousePassP2PApp
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

@Composable
fun CreateEntryFab(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        // Icone + placeholder
        Text("+")
    }
}

@Preview
@Composable
fun CreateEntryFabPreview(){
    HousePassP2PTheme {
        CreateEntryFab {  }
    }
}