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
    var supportingText: String by mutableStateOf(IndicationCodeText)
        private set
    var isErrorLimitReached by mutableStateOf(false)
        private set
    private var _errorCount = 0
    var isLoading by mutableStateOf(false)
        private set

    fun onCodeChange(newCode: CharArray){
        if (newCode.isEmpty()){
            supportingText = IndicationCodeText
        }

        if (newCode.size <= Constants.CodeCharCount){
            isButtonEnabled = (newCode.size == Constants.CodeCharCount && !isLoading)
        }
    }

    fun onConfirm(newCode: CharArray){
        isLoading  = true

        viewModelScope.launch {
            try {
                // TODO replace later the equal obviously
                val realCode = CharArray(Constants.CodeCharCount)
                realCode.fill(Char(1))

                if (newCode.contentEquals(realCode)) {
                    isErrorState = false
                    supportingText = ""
                    _errorCount = 0

                } else {
                    isErrorState = true
                    supportingText = WrongCodeText
                    _errorCount += 1
                }
                isErrorLimitReached = _errorCount == ErrorLimit

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

private const val WrongCodeText = "Retry your password"
private const val IndicationCodeText = "Enter your PIN code"
private const val ErrorLimit = 5