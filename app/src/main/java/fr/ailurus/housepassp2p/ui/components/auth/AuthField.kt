package fr.ailurus.housepassp2p.ui.components.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.AppDimensions.AuthFieldWidth
import fr.ailurus.housepassp2p.BordersConfig.ShapeMediumUpperAngle
import fr.ailurus.housepassp2p.PinConfig.LetterSpacing
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme
import fr.ailurus.housepassp2p.ui.theme.utils.PinVisualTransformation

@Composable
fun AuthField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    enabled: Boolean = true,
    supportingText: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = PinVisualTransformation(),
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
//        label = { Text(label) },
        isError = isError,
        enabled = enabled,
        modifier = modifier.width(AuthFieldWidth),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
            ),
        singleLine = true,
        textStyle =
            LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                letterSpacing = LetterSpacing,
            ),
        shape = ShapeMediumUpperAngle,
        colors =
            TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                errorContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            ),
    )
}

@Preview(showBackground = true)
@Composable
fun AuthFieldPreview() {
    HousePassP2PTheme {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AuthField(
                value = "1234",
                onValueChange = {},
                label = "PIN code",
                supportingText = { Text("Enter your PIN code") },
            )
        }
    }
}
