package fr.ailurus.housepassp2p.ui.components.vault

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.data.entities.EntrySummary
import fr.ailurus.housepassp2p.data.uidatas.EntryCard
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

@Composable
fun EntryListDisplay(
    entries: List<EntryCard>,
    onEntryClick: (EntryCard) -> Unit
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