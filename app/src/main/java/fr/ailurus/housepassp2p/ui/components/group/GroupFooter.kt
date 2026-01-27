package fr.ailurus.housepassp2p.ui.components.group

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.ailurus.housepassp2p.AppDimensions

@Composable
fun GroupFooter(
    modifier: Modifier = Modifier,

    onValidation: () -> Unit
) {

    val commonModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = AppDimensions.PaddingSmall)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = AppDimensions.PaddingSmall),
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            modifier = commonModifier,
            onClick = onValidation,
            content = { Text("Save changes") }
        )
    }
}