package fr.ailurus.housepassp2p.data.uidatas

import fr.ailurus.housepassp2p.data.entities.GroupSummary

data class MainScreenState(
    val entries: List<EntryCard> = emptyList(),
    val groups: List<GroupSummary> = emptyList(),
    val searchQuery: String = "",
    val selectedGroup: GroupSummary? = null,
    val isEditorOpen: Boolean = false,
)
