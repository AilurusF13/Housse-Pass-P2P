package fr.ailurus.housepassp2p.ui.components.vault

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

// SearchBar + Sync buton

@Composable
fun SearchRow(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    onSyncClick: () -> Unit
) {
    Row {
        // Barre de recherche
        TextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            placeholder = { Text("Rechercher...") },
            modifier = Modifier.weight(1f) // Prend l'espace disponible
        )

        // Bouton Sync
        Button(onClick = onSyncClick) {
            Text("Sync")
        }
    }
}

@Preview
@Composable
fun SearchRowPreview() {
    HousePassP2PTheme {
        SearchRow(
            searchQuery = "Exemple",
            onSearchChange = {},
            onSyncClick = {}
        )
    }
}