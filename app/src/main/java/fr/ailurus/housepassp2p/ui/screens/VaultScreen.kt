package fr.ailurus.housepassp2p.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
    // 1. Collecte des états
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val groups by viewModel.groups.collectAsState()
    val entries by viewModel.entries.collectAsState()

    // 2. Scaffold principal
    Scaffold(
        topBar = { VaultTopBar() },
        floatingActionButton = {
            FloatingActionButton({

            }) {
                // Icone + placeholder
                Text("+")
            }
        }
    ) { paddingValues ->

        // 3. Contenu principal
        Column(modifier = Modifier.padding(paddingValues)) {

            // Row : Recherche + Sync
            SearchRow (
                searchQuery = searchQuery,
                onSearchChange = { viewModel.onSearchQueryChange(it) },
                onSyncClick = { /* TODO Sync action */ }
            )

            // Row : Filtres
            FilterChipsRow(
                groups = groups,
                selectedGroup = uiState.selectedGroup,
                onGroupSelected = { viewModel.onGroupSelected(it) }
            )

            // Column : Liste des entrées
            EntryListDisplay(
                entries = entries,
                onEntryClick = { /* Future: open edit mode */ }
            )
        }

        // 4. Dialogue conditionnel
        if (uiState.isEditorOpen) {
            EditEntryDialog(
                onDismiss = { viewModel.onCloseEditor() },
                onSave = { viewModel.onSaveEntry() }
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview(){
    HousePassP2PTheme {
        VaultScreen()
    }
}