package fr.ailurus.housepassp2p.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.ailurus.housepassp2p.Constants
import fr.ailurus.housepassp2p.ui.components.auth.AuthButton
import fr.ailurus.housepassp2p.ui.components.auth.AuthCard
import fr.ailurus.housepassp2p.ui.components.auth.AuthField
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme
import fr.ailurus.housepassp2p.ui.theme.utils.shake
import fr.ailurus.housepassp2p.ui.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
){
    val context = LocalContext.current
    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(viewModel.isErrorState) {
        if (viewModel.isErrorState){
            shakeOffset.shake()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
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
                        value = viewModel.pinCode,
                        onValueChange = {
                                viewModel.onCodeChange(it)
                            },
                        label = "PIN code",
                        supportingText = { Text(viewModel.supportingText) },
                        isError = viewModel.isErrorState
                    )
                    AuthButton(
                        modifier = Modifier.padding(top = AppDimensions.PaddingExtraLarge),
                        text = "Next",
                        onClick = {
                            viewModel.onConfirm()
                            if (viewModel.isConfirmationSuccessful) {
                                Toast.makeText(context, "Opening vault", Toast.LENGTH_SHORT).show()
                            }
                        },
                        enabled = viewModel.isButtonEnabled
                    )

                    if (viewModel.isErrorLimitReached){
                        Text(
                            modifier = Modifier
                                .padding(top = AppDimensions.PaddingLarge)
                                .clickable{
                                Toast.makeText(context, "Erasing vault", Toast.LENGTH_LONG ).show() // TODO change
                            }
                            ,
                            text =  "Erase vault and restore password",
                            textDecoration = TextDecoration.Underline,
                            fontStyle = FontStyle.Italic,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        )
                    }
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