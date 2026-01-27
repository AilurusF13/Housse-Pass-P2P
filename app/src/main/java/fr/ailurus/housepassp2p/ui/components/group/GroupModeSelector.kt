package fr.ailurus.housepassp2p.ui.components.group

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.BordersConfig
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

@Composable
fun GroupModeSelector(
    modifier: Modifier = Modifier,
    selectedMode: GroupModeOption = GroupModeOption.EDIT,
    actions: Map<GroupModeOption, () -> Unit>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = AppDimensions.PaddingSmall)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceEvenly,

        ) {

        GroupModeOption.entries.forEach { option ->
            FilterChip(
                modifier = Modifier.padding(horizontal = AppDimensions.PaddingSmall),
                onClick = { actions[option]?.invoke() },
                selected = option == selectedMode, // not good
                shape = BordersConfig.ShapeMedium,
                label = {
                    Text(
                        modifier = Modifier.padding(all = AppDimensions.PaddingLarge),
                        text = option.label.uppercase(),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupModeSelectorPreview() {
    HousePassP2PTheme {
        GroupModeSelector(
            actions = mapOf(
                GroupModeOption.EDIT to {},
                GroupModeOption.SHARE to {}
            )
        )
    }
}