@file:OptIn(ExperimentalMaterial3Api::class)

package fr.ailurus.housepassp2p.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
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
    val editorUiState = viewModel.editorState

    Scaffold(
        modifier = modifier,
        topBar = { VaultTopBar() },
        floatingActionButton = {
            FloatingActionButton({ viewModel.onAddAction() }) {
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
                onEntryClick = { viewModel.onEditAction(entry = it) }
                // Edit should be triggered on long click
            )

            // Mock entry dialog
            if (uiState.isEditorOpen){
                AlertDialog(
                    content = { Text(editorUiState.editorState?.site ?: "Error") },
                    onDismissRequest = { viewModel.onCloseEditor() }
                )
            }

        }
    }
}