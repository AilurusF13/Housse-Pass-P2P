package fr.ailurus.housepassp2p.ui.components.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.data.entities.EntrySummary
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

@Composable
fun EntryListDisplay(
    entries: List<EntrySummary>,
    onEntryClick: (EntrySummary) -> Unit
) {
    LazyColumn {
        items(entries) { entry ->
            // Card simple sans style
            Card(
                onClick = { onEntryClick(entry) }
            ) {
                Text(text = entry.site)
                // D'autres infos de l'entry ici
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EntryListDisplayPreview(){
    HousePassP2PTheme {
        EntryListDisplay(
            entries = listOf(
                EntrySummary(1, "Site1", "Login1", 1),
                EntrySummary(2, "Site2", "Login2", 2),
                EntrySummary(3, "Site3", "Login3", 3)
            ),
            onEntryClick = {}
        )
    }
}