package fr.ailurus.housepassp2p.ui.components.vault

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.BordersConfig
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme


// SearchBar + Sync buton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRow(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    onSyncClick: () -> Unit // TODO Should be a sync manager class instead
) {

    val focusRequester = remember { FocusRequester() }

    Row {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppDimensions.PaddingLarge)
                .focusRequester(focusRequester),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search")
            },
            placeholder = {
                Text("Find your password")
            },
            trailingIcon = {
                IconButton(onClick = { /* TODO Sync action */ }) {
                    Icon(
                        imageVector = Icons.Default.Sync,
                        contentDescription = "Synchronize",
                    )
                }
            },
            shape = BordersConfig.ShapeLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchRowPreview() {
    HousePassP2PTheme {
        SearchRow(
            searchQuery = "",
            onSearchChange = {},
            onSyncClick = {}
        )
    }
}