package fr.ailurus.housepassp2p.ui.components.vault

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.data.entities.Entry
import fr.ailurus.housepassp2p.data.uidatas.EntryCard

@Composable
fun EntryListDisplay(
    entries: List<EntryCard>,
    onEntryClick: (EntryCard) -> Unit,
    onPasswordCopy : (EntryCard) -> Unit = {},
    onLoginCopy : (EntryCard) -> Unit = {},
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize() // or width
    ) {
        items(entries) { entry ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = AppDimensions.PaddingMedium,
                        vertical = AppDimensions.PaddingSmall
                    )
                    .combinedClickable(
                        onClick = { /* TODO Action simple click */ },
                        onLongClick = { onEntryClick(entry) }
                    )
            ) {
                // The arrangement is 10 70 10 10
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppDimensions.PaddingMedium),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier.weight(0.1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = entry.site[0].toString(),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    // MIDDLE
                    Column(
                        modifier = Modifier
                            .weight(0.7f)
                            .padding(horizontal = AppDimensions.PaddingExtraLarge)
                    ) {
                        // SITE NAME
                        Text(
                            text = entry.site,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        // LOGIN
                        Text(
                            text = entry.login,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.typography.bodyMedium.color.copy(alpha = 0.7f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    // ICONS
                    Box(
                        modifier = Modifier.weight(0.1f),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { onLoginCopy }) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Copy",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier.weight(0.1f),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { onPasswordCopy }) {
                            Icon(
                                imageVector = Icons.Default.Key,
                                contentDescription = "Copy",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}