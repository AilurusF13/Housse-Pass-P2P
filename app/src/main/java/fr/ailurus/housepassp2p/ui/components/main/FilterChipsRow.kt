package fr.ailurus.housepassp2p.ui.components.main

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

@Composable
fun FilterChipsRow(
    groups: List<GroupSummary>,
    selectedGroup: GroupSummary?,
    onGroupSelected: (GroupSummary) -> Unit
) {
    Row {
        groups.forEach { group ->
            FilterChip(
                selected = group == selectedGroup,
                onClick = { onGroupSelected(group) },
                label = { Text(group.name) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterChipsRowPreview(){
    HousePassP2PTheme {
        FilterChipsRow(
            groups = listOf(
                GroupSummary(1, "Group1", "Description1"),
                GroupSummary(2, "Group2", "Description2"),
                GroupSummary(3, "Group3", "Description3")
            ),
            selectedGroup = null,
            onGroupSelected = {}
        )
    }
}