package fr.ailurus.housepassp2p.data.entities

data class PasswordUiState(
    val isEditorOpen: Boolean = false,
    val selectedGroup: GroupSummary? = null
)