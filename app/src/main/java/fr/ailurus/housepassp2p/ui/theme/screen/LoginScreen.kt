package fr.ailurus.housepassp2p.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.ui.theme.AppDimensions.PaddingExtraLarge
import fr.ailurus.housepassp2p.ui.theme.AppDimensions.PaddingSmall
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme
import fr.ailurus.housepassp2p.ui.theme.components.auth.AuthButton
import fr.ailurus.housepassp2p.ui.theme.components.auth.AuthCard
import fr.ailurus.housepassp2p.ui.theme.components.auth.AuthField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthCard(
            modifier = modifier,
            content = {
                Column(
                    modifier = Modifier
                        .padding(PaddingExtraLarge),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    AuthField(
                        value = "1234",
                        onValueChange = {},
                        label = "PIN code",
                        supportingText = { Text("Enter your PIN code") }
                    )
                    AuthButton(
                        modifier = Modifier.padding(top = PaddingSmall),
                        text = "Next",
                        onClick = {},
                        enabled = true
                    )

                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    HousePassP2PTheme {
        LoginScreen()
    }
}