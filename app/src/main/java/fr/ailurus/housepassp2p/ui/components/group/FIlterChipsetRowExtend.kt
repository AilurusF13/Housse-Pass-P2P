package fr.ailurus.housepassp2p.ui.components.group

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import fr.ailurus.housepassp2p.ui.components.vault.FilterChipsRow

@Composable
fun FilterChipsetRowExtend(
    modifier: Modifier = Modifier,
    groups: List<GroupSummary>,

    selectedGroup: GroupSummary?,
    onGroupSelected: (GroupSummary?) -> Unit,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterChip(
            modifier = Modifier.padding(horizontal = AppDimensions.PaddingSmall),
            selected = selectedGroup == null,
            onClick = { onGroupSelected(null) },
            label = { Text("New group") }
        )

        FilterChipsRow(
            groups, selectedGroup, onGroupSelected
        )
    }

}