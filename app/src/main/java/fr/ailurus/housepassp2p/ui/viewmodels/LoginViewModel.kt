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
    var pinCode by mutableStateOf("")
        private set

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

    fun onCodeChange(newCode: String){
        isErrorState = false
        if (newCode.length <= Constants.CODE_CHAR_COUNT){
            pinCode = newCode
            supportingText = IndicationCodeText
            isButtonEnabled = (newCode.length == Constants.CODE_CHAR_COUNT && !isLoading)
        }
    }

    fun onConfirm(){
        isLoading  = true

        val codeToCheck = pinCode.toCharArray()

        viewModelScope.launch {
            try {
                // TODO replace later the equal obviously
                val realCode = CharArray(Constants.CODE_CHAR_COUNT) { '1' }

                if (realCode.contentEquals(codeToCheck)) {
                    _errorCount = 0
                    isConfirmationSuccessful = true

                } else {
                    isErrorState = true
                    supportingText = WrongCodeText
                    _errorCount += 1
                }
                pinCode = ""
                isButtonEnabled = false
                isErrorLimitReached = _errorCount >= ErrorLimit

            } catch(e: Exception) {
            } finally {
                codeToCheck.fill(Char(0))
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