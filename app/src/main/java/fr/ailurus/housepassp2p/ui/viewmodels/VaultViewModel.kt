package fr.ailurus.housepassp2p.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import fr.ailurus.housepassp2p.data.repository.RepositoryManager
import fr.ailurus.housepassp2p.data.uidatas.EntryCard
import fr.ailurus.housepassp2p.data.uidatas.MainScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class VaultViewModel(
    repositoryManager: RepositoryManager
): ViewModel() {

    // ---- VARIABLES
    private val _selectedGroup = MutableStateFlow<GroupSummary?>(null)
    private val _searchQuery = MutableStateFlow("")
    private val _isEditorOpen = MutableStateFlow(false)

    val uiState: StateFlow<MainScreenState> = combine (
        repositoryManager.getEntries(),
        repositoryManager.getGroups(),
        _searchQuery,
        _selectedGroup,
        _isEditorOpen
    ){
        allEntries, allGroups, searchQuery, selectedGroup, isEditorOpen ->

        val groupMap = allGroups.associateBy { it.groupId }

        val filteredCards = allEntries
            .filter { entry ->
                val matchSearch = searchQuery.isBlank() ||
                        entry.site.contains(searchQuery, ignoreCase = true)
                val matchGroup = selectedGroup == null || entry.groupId == selectedGroup.groupId
                matchSearch && matchGroup
            }.map { entry ->
                EntryCard(
                    id = entry.id,
                    site = entry.site,
                    login = entry.login,
                    group = groupMap[entry.groupId]
                )
            }
        MainScreenState(
            entries = filteredCards,
            groups = allGroups,
            searchQuery = searchQuery,
            selectedGroup = selectedGroup,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainScreenState()
    )

    // ---- FUNCTIONS

    fun onSearchQueryChange(query: String){
        _searchQuery.value = query
    }

    fun onGroupSelected(group: GroupSummary){
        _selectedGroup.value = if (group == _selectedGroup.value)
            null else group
    }

    fun onOpenEditor(){
        _isEditorOpen.value = true
    }
    fun onCloseEditor(){
        _isEditorOpen.value = false
    }
}
