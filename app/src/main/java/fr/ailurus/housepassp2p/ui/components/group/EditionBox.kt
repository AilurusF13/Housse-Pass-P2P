package fr.ailurus.housepassp2p.ui.components.group

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.BordersConfig
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

@Composable
fun EditionBox(
    selectedGroup: GroupSummary?,
    onConfirmAction: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(horizontal = AppDimensions.PaddingLarge),
            color = MaterialTheme.colorScheme.surfaceContainer,
            shape = BordersConfig.ShapeLarge,
            tonalElevation = 8.dp, // TODO aesthetic change ?
            shadowElevation = 8.dp,
        ) {
            Column(
                Modifier.padding(all = AppDimensions.PaddingLarge)
            ) {
                GroupFields(
                    modifier = Modifier.fillMaxWidth(),
                    name = selectedGroup?.name ?: "",
                    onNameChange = {},
                    description = selectedGroup?.description ?: "",
                    onDescriptionChange = {}
                )

                Spacer(Modifier.padding(all = AppDimensions.PaddingMedium))

                GroupFooter(
                    onValidation = onConfirmAction
                )
            }
        }
    }
}

@Preview
@Composable
fun EditionBoxPreview() {
    HousePassP2PTheme {
        EditionBox(
            null,
            {}
        )
    }
}