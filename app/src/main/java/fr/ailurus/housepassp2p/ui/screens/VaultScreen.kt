@file:OptIn(ExperimentalMaterial3Api::class)

package fr.ailurus.housepassp2p.ui.screens

import android.content.ClipData
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.Clipboard
import androidx.compose.ui.platform.LocalClipboard
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.ailurus.housepassp2p.ui.components.vault.EditEntryDialog
import fr.ailurus.housepassp2p.ui.components.vault.EntryListDisplay
import fr.ailurus.housepassp2p.ui.components.vault.FilterChipsRow
import fr.ailurus.housepassp2p.ui.components.vault.SearchRow
import fr.ailurus.housepassp2p.ui.viewmodels.AppViewModelProvider
import fr.ailurus.housepassp2p.ui.viewmodels.VaultViewModel
import kotlinx.coroutines.launch

@Composable
fun VaultScreen (
    modifier: Modifier = Modifier,
    viewModel: VaultViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {

    val uiState by viewModel.uiState.collectAsState()
    val editorUiState = viewModel.editorState

    val clipboard: Clipboard = LocalClipboard.current

    Scaffold(
        modifier = modifier,
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
                onEntryClick = { viewModel.onEditAction(entry = it) },
                // Edit should be triggered on long click
                onPasswordCopy = {
                    viewModel.viewModelScope.launch {
                        val clipData= ClipData.newPlainText("password", viewModel.getPassword(it))
                        val clipEntry = ClipEntry(clipData)
                        clipboard.setClipEntry(clipEntry)
                    }
                },
                onLoginCopy = {
                    viewModel.viewModelScope.launch {
                        val clipData= ClipData.newPlainText("login", it.login)
                        val clipEntry = ClipEntry(clipData)
                        clipboard.setClipEntry(clipEntry)
                    }
                }
            )

            // Mock entry dialog
            if (uiState.isEditorOpen){
                EditEntryDialog(
                    editorStateView = editorUiState,
                    onDismiss = { viewModel.onCloseEditor() },
                )
            }
        }
    }
}
