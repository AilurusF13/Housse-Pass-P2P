package fr.ailurus.housepassp2p.ui.components.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.ui.theme.AppDimensions.PaddingExtraLarge
import fr.ailurus.housepassp2p.ui.theme.AppDimensions.PaddingMedium
import fr.ailurus.housepassp2p.ui.theme.BordersConfig.ShapeMedium
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
){
    Button(
        enabled = enabled,
        onClick = onClick,
        shape = ShapeMedium,
        contentPadding = PaddingValues(
            PaddingExtraLarge,
            PaddingMedium,
            PaddingExtraLarge,
            PaddingMedium,
        ),
        modifier = modifier
    ) {
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun AuthButtonPreview(){
    HousePassP2PTheme {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            AuthButton(
                text ="Next",
                onClick = {},
                enabled = true,
            )
        }
    }
}
