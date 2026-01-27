package fr.ailurus.housepassp2p.ui.components.group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.BordersConfig

@Composable
fun GroupFieldUnique(
    modifier: Modifier = Modifier,

    name: String,
    description: String = "",
    value: String,
    onValueChange: (String) -> Unit,
    lineNumber: Int = 5
) {

    Column(
        modifier = modifier
    ) {
        OutlinedTextField( // alternative color for visual impact
            modifier = Modifier
                .padding(
                    top = AppDimensions.PaddingMedium,
                    start = AppDimensions.PaddingMedium,
                    end = AppDimensions.PaddingMedium,
                    bottom = AppDimensions.PaddingSmall
                )
                .fillMaxWidth(),
            value = name,
            onValueChange = { },
            readOnly = true,
            shape = BordersConfig.ShapeLarge,
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                // TODO style as a title
                textAlign = TextAlign.Center,
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(
                    top = AppDimensions.PaddingSmall,
                    start = AppDimensions.PaddingMedium,
                    end = AppDimensions.PaddingMedium,
                    bottom = AppDimensions.PaddingMedium,
                )
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            shape = BordersConfig.ShapeLarge,
            maxLines = lineNumber,
            minLines = lineNumber,
            placeholder = { Text(description) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GroupFieldUniquePreview() {
    GroupFieldUnique(
        name = "Name",
        value = "HELLOOOO  EVEYRONE ",
        onValueChange = {}
    )
}