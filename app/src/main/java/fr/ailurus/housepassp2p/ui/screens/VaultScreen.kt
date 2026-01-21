package fr.ailurus.housepassp2p.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.ailurus.housepassp2p.ui.components.vault.EditEntryDialog
import fr.ailurus.housepassp2p.ui.components.vault.EntryListDisplay
import fr.ailurus.housepassp2p.ui.components.vault.FilterChipsRow
import fr.ailurus.housepassp2p.ui.components.vault.SearchRow
import fr.ailurus.housepassp2p.ui.components.vault.VaultTopBar
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme
import fr.ailurus.housepassp2p.ui.viewmodels.VaultViewModel
import fr.ailurus.housepassp2p.ui.viewmodels.AppViewModelProvider

@Composable
fun VaultScreen (
    modifier: Modifier = Modifier,
    viewModel: VaultViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = { VaultTopBar() },
        floatingActionButton = {
            FloatingActionButton({
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add entry")
            }
        }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {

            SearchRow (
                searchQuery = uiState.searchQuery,
                onSearchChange = { viewModel.onSearchQueryChange(it) },
                onSyncClick = { /* TODO Sync action */ }
            )

            FilterChipsRow(
                groups = uiState.groups,
                selectedGroup = uiState.selectedGroup,
                onGroupSelected = { viewModel.onGroupSelected(it) }
            )

            EntryListDisplay(
                entries = uiState.entries,
                onEntryClick = { /* Future: open edit mode */ }
            )
        }
    }
}