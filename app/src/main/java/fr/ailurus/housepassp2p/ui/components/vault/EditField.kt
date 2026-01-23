package fr.ailurus.housepassp2p.ui.components.vault

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.BordersConfig.ShapeMedium

@Composable
fun EditField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Box (
        modifier = modifier
            .padding(vertical = AppDimensions.PaddingSmall)
    ){ // or surface

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            visualTransformation = visualTransformation,

            singleLine = true,
            shape = ShapeMedium
        )
    }
}