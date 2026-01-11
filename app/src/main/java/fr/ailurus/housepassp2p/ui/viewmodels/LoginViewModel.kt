package fr.ailurus.housepassp2p.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import fr.ailurus.housepassp2p.Constants
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel(
    /* Nothing yet */
) {
    var isButtonEnabled by mutableStateOf(false)
        private set
    var isErrorState by mutableStateOf(false)
        private set

    val nullSupportText = ""
    var supportingText: String by mutableStateOf(IndicationCodeText)
        private set
    var isErrorLimitReached by mutableStateOf(false)
        private set
    var isLoading by mutableStateOf(false)
        private set
    var isConfirmationSuccessful by mutableStateOf(false)
        private set
    private var _errorCount = 0

    fun onCodeChange(newCode: CharArray){
        isErrorState = false
        supportingText = if (newCode.isEmpty()) IndicationCodeText else nullSupportText

        if (newCode.size <= Constants.CODE_CHAR_COUNT){
            isButtonEnabled = (newCode.size == Constants.CODE_CHAR_COUNT && !isLoading)
        }
    }

    fun onConfirm(newCode: CharArray){
        isLoading  = true
        isConfirmationSuccessful = false

        viewModelScope.launch {
            try {
                // TODO replace later the equal obviously
                val realCode = CharArray(Constants.CODE_CHAR_COUNT) { '1' }

                if (newCode.contentEquals(realCode)) {
                    isErrorState = false
                    supportingText = nullSupportText
                    _errorCount = 0
                    isConfirmationSuccessful = true

                } else {
                    isErrorState = true
                    supportingText = WrongCodeText
                    _errorCount += 1
                }
                isErrorLimitReached = _errorCount >= ErrorLimit

            } catch(e: Exception) {
            } finally {
                newCode.fill(Char(0))
                isLoading = false
            }
        }
    }

    val loginViewModelFactory = viewModelFactory {
        initializer {
            LoginViewModel()
        }
    }
}

private const val WrongCodeText = "Retype your code"
private const val IndicationCodeText = "Enter your PIN code"
private const val ErrorLimit = 5