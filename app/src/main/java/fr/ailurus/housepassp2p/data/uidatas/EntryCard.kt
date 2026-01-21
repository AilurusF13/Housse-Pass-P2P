package fr.ailurus.housepassp2p.data.uidatas

import fr.ailurus.housepassp2p.data.entities.GroupSummary

data class EntryCard(
    val id: Int,
    val site: String,
    val login: String,
    val group: GroupSummary? // Takes a little bit more space for each entry card but fasten the processus
)
