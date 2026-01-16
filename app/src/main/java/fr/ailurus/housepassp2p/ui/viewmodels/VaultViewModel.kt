package fr.ailurus.housepassp2p.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import fr.ailurus.housepassp2p.data.entities.EntrySummary
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import fr.ailurus.housepassp2p.data.entities.PasswordUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.collections.emptyList

class VaultViewModel: ViewModel() {

    // code here
    val uiState = MutableStateFlow(PasswordUiState())
    val searchQuery = MutableStateFlow("")
    val groups = MutableStateFlow<List<GroupSummary>>(emptyList())
    val entries = MutableStateFlow<List<EntrySummary>>(emptyList()) // Cette liste sera déjà filtrée par le VM

    // Actions
    fun onSearchQueryChange(query: String) {}
    fun onGroupSelected(group: GroupSummary) {}
    fun onOpenEditor() {}
    fun onCloseEditor() {}
    fun onSaveEntry() {}

    companion object {
        val vaultViewModelFactory =
            viewModelFactory {
                initializer {
                    VaultViewModel()
                }
            }
    }
}
