package fr.ailurus.housepassp2p.ui.components.vault

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

@Composable
fun FilterChipsRow(
    groups: List<GroupSummary>,
    selectedGroup: GroupSummary?,
    onGroupSelected: (GroupSummary) -> Unit
) {
    val scrollState = rememberScrollState()

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AppDimensions.PaddingMedium)
            .horizontalScroll(scrollState)
    ){
        groups.forEach { group ->
            FilterChip(
                modifier = Modifier.padding(horizontal = AppDimensions.PaddingSmall),
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
