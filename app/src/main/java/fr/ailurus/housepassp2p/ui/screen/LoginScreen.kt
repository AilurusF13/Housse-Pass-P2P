package fr.ailurus.housepassp2p.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.ailurus.housepassp2p.ui.theme.AppDimensions
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme
import fr.ailurus.housepassp2p.ui.components.auth.AuthButton
import fr.ailurus.housepassp2p.ui.components.auth.AuthCard
import fr.ailurus.housepassp2p.ui.components.auth.AuthField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
//    viewModel: LoginViewModel,
){
    Column(
        modifier = Modifier.Companion.fillMaxSize(),
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthCard(
            modifier = modifier,
            content = {
                Column(
                    modifier = Modifier.Companion
                        .padding(AppDimensions.PaddingExtraLarge),

                    horizontalAlignment = Alignment.Companion.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    AuthField(
                        value = "1234",
                        onValueChange = {},
                        label = "PIN code",
                        supportingText = { Text("Enter your PIN code") }
                    )
                    AuthButton(
                        modifier = Modifier.Companion.padding(top = AppDimensions.PaddingSmall),
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