package fr.ailurus.housepassp2p.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import fr.ailurus.housepassp2p.ui.components.group.EditionBox
import fr.ailurus.housepassp2p.ui.components.group.FilterChipsetRowExtend
import fr.ailurus.housepassp2p.ui.components.group.GroupHeader
import fr.ailurus.housepassp2p.ui.components.group.GroupModeOption
import fr.ailurus.housepassp2p.ui.components.group.GroupModeSelector
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

@Composable
fun GroupScreen(
    modifier: Modifier = Modifier,

    groups: List<GroupSummary>,
    selectedGroup: GroupSummary?,
    onGroupSelected: (GroupSummary?) -> Unit, // different from filterChipsRow here

    onConfirmAction: () -> Unit
) {

    var mode by remember { mutableStateOf(GroupModeOption.EDIT) }

    val actions = GroupModeOption.entries.associateWith { option -> { mode = option } }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
        ) {

            GroupHeader()

            FilterChipsetRowExtend(
                groups = groups,
                selectedGroup = selectedGroup,
                onGroupSelected = onGroupSelected,
            )

            GroupModeSelector(
                selectedMode = mode,
                actions = actions,
            )

            when (mode) {
                GroupModeOption.EDIT -> {
                    EditionBox(
                        selectedGroup,
                        onConfirmAction,
                    )
                }

                GroupModeOption.SHARE -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupScreenPreview() {
    HousePassP2PTheme {
        GroupScreen(
            groups = listOf(
                GroupSummary(1, "Group1", "Description1"),
                GroupSummary(2, "Group2", "Description2"),
                GroupSummary(3, "Group3", "Description3"),
                GroupSummary(4, "Group4", "Description4")
            ),
            selectedGroup = null,
            onGroupSelected = {},

            onConfirmAction = {},
        )
    }
}
