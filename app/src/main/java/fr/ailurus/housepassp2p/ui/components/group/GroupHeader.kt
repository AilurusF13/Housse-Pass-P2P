package fr.ailurus.housepassp2p.ui.components.group

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.BordersConfig

@Composable
fun GroupHeader() {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppDimensions.PaddingLarge)
            .navigationBarsPadding(),
        value = "Groups",
        onValueChange = {},
        shape = BordersConfig.ShapeLarge,
        readOnly = true,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Group, contentDescription = "Group title")
        },
    )
}