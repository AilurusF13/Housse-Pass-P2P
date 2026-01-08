package fr.ailurus.housepassp2p.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.ailurus.housepassp2p.Constants
import fr.ailurus.housepassp2p.ui.components.auth.AuthButton
import fr.ailurus.housepassp2p.ui.components.auth.AuthCard
import fr.ailurus.housepassp2p.ui.components.auth.AuthField
import fr.ailurus.housepassp2p.ui.theme.AppDimensions
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme
import fr.ailurus.housepassp2p.ui.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
){
    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(viewModel.isErrorState) {
        if (viewModel.isErrorState) {
            val intensity = 15f
            shakeOffset.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 400
                    (-intensity) at 50 // à 50ms, on est à -20dp
                    (intensity) at 150
                    (-intensity) at 250
                    (intensity) at 350
                    0f at 400
                }
            )
        }
    }

    var code by remember {mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthCard(
            modifier = Modifier.offset(x = shakeOffset.value.dp),
            content = {
                Column(
                    modifier = Modifier
                        .padding(AppDimensions.PaddingExtraLarge),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    AuthField(
                        value = code,
                        onValueChange = {
                                code = if (it.length <= Constants.CodeCharCount) it else code
                                viewModel.onCodeChange(it.toCharArray())
                            },
                        label = "PIN code",
                        supportingText = { viewModel.supportingText?.let { Text(it) } },
                        isError = viewModel.isErrorState
                    )
                    AuthButton(
                        modifier = Modifier.padding(top = AppDimensions.PaddingSmall),
                        text = "Next",
                        onClick = {
                            viewModel.onConfirm(code.toCharArray())
                            code = ""
                        },
                        enabled = viewModel.isButtonEnabled
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
        LoginScreen(viewModel = LoginViewModel())
    }
}