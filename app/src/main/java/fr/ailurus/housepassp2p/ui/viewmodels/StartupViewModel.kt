package fr.ailurus.housepassp2p.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class StartupViewModel : ViewModel(

){

    var isErrorState by mutableStateOf(false)
        private set

    var isButtonEnabled by mutableStateOf(false)
        private set

    val nullSupportText = ""
    var codeSupportText: String by mutableStateOf(IndicationCodeText)
        private set

    var confirmSupportText: String by mutableStateOf(IndicationConfirmText)
        private set


    val loginViewModelFactory = viewModelFactory {
        initializer {
            LoginViewModel()
        }
    }

    fun onCodeChange(newCode: CharArray) {}

    fun onConfirmChange(newCode: CharArray) {}

    suspend fun onConfirm(){}
}

private const val IndicationCodeText = "Enter your PIN code"
private const val IndicationConfirmText = "Confirm your PIN code"
