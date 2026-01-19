package fr.ailurus.housepassp2p.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import fr.ailurus.housepassp2p.data.entities.EntrySummary
import fr.ailurus.housepassp2p.data.entities.Group
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import fr.ailurus.housepassp2p.data.entities.PasswordUiState
import fr.ailurus.housepassp2p.data.repository.RepositoryManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlin.collections.emptyList

class VaultViewModel(
    repositoryManager: RepositoryManager
): ViewModel() {

    // code here
    val uiState = MutableStateFlow(PasswordUiState())
    val searchQuery = MutableStateFlow("")

    val groups : StateFlow<List<GroupSummary>> = repositoryManager.getGroups()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    // was wrote first because of the necessity of a visual call in order for the callback creating and setting up database to be called

    val entries = MutableStateFlow<List<EntrySummary>>(emptyList()) // Cette liste sera déjà filtrée par le VM

    // Actions
    fun onSearchQueryChange(query: String) {}
    fun onGroupSelected(group: GroupSummary) {}
    fun onOpenEditor() {}
    fun onCloseEditor() {}
    fun onSaveEntry() {}
}
