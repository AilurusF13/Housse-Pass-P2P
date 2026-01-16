package fr.ailurus.housepassp2p.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.Animatable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.ailurus.housepassp2p.AppDimensions
import fr.ailurus.housepassp2p.Constants
import fr.ailurus.housepassp2p.ui.components.auth.AuthButton
import fr.ailurus.housepassp2p.ui.components.auth.AuthCard
import fr.ailurus.housepassp2p.ui.components.auth.AuthField
import fr.ailurus.housepassp2p.ui.theme.HousePassP2PTheme
import fr.ailurus.housepassp2p.ui.theme.utils.shake
import fr.ailurus.housepassp2p.ui.viewmodels.SetupViewModel

@Composable
fun StartupScreen(
    modifier: Modifier = Modifier,
    viewModel: SetupViewModel = viewModel(factory = SetupViewModel.setupViewModelFactory),
) {
    val context = LocalContext.current
    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(viewModel.isErrorState) {
        if (viewModel.isErrorState) {
            shakeOffset.shake()
        }
    }

    var code by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AuthCard(
            modifier = Modifier.offset(x = shakeOffset.value.dp),
            content = {
                Column(
                    modifier =
                        Modifier
                            .padding(AppDimensions.PaddingExtraLarge),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    // Auth field normal
                    AuthField(
                        value = code,
                        onValueChange = {
                            code = if (it.length <= Constants.CODE_CHAR_COUNT) it else code
                            viewModel.onCodeChange(it)
                        },
                        label = "PIN code",
                        supportingText = { Text(viewModel.codeSupportText) },
                        isError = false,
                    )

                    // Auth field confirmation
                    AuthField(
                        modifier = Modifier.padding(top = AppDimensions.PaddingExtraLarge),
                        value = confirm,
                        onValueChange = {
                            confirm = if (it.length <= Constants.CODE_CHAR_COUNT) it else confirm
                            viewModel.onConfirmChange(it)
                        },
                        label = "Confirm PIN code",
                        supportingText = { Text(viewModel.confirmSupportText) },
                        isError = viewModel.isErrorState,
                    )

                    // Confirm Button
                    AuthButton(
                        modifier = Modifier.padding(top = AppDimensions.PaddingExtraLarge),
                        text = "Create",
                        onClick = {
                            viewModel.onConfirm()
                            Toast.makeText(context, "Setting up database", Toast.LENGTH_LONG).show()
                        },
                        enabled = viewModel.isButtonEnabled,
                    )
                }
            },
        )
    }
}

@Preview
@Composable
fun StartupScreenPreview() {
    HousePassP2PTheme {
        StartupScreen(viewModel = SetupViewModel(), modifier = Modifier)
    }
}
