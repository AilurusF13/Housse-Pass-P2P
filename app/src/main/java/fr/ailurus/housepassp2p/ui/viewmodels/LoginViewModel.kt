package fr.ailurus.housepassp2p.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import fr.ailurus.housepassp2p.Constants
import fr.ailurus.housepassp2p.data.repository.RepositoryManager
import kotlinx.coroutines.launch

class LoginViewModel(
    val repositoryManager: RepositoryManager
) :
    ViewModel() {
    var pinCode by mutableStateOf("")
        private set
    var isButtonEnabled by mutableStateOf(false)
        private set
    var isErrorState by mutableStateOf(false)
        private set
    var supportingText: String by mutableStateOf(INDICATION_CODE_TEXT)
        private set
    var isErrorLimitReached by mutableStateOf(false)
        private set
    var isLoading by mutableStateOf(false)
        private set
    var isConfirmationSuccessful by mutableStateOf(false)
        private set
    private var errorCount = 0

    fun onCodeChange(newCode: String) {
        isErrorState = false
        if (newCode.length <= Constants.CODE_CHAR_COUNT) {
            pinCode = newCode
            supportingText = INDICATION_CODE_TEXT
            isButtonEnabled = (newCode.length == Constants.CODE_CHAR_COUNT && !isLoading)
        }
    }

    fun onConfirm() {
        isLoading = true

        val codeToCheck = pinCode.toCharArray()

        viewModelScope.launch {
            try {
                val res = repositoryManager.openVault(pinCode.toByteArray())
                // if succeeds, the vault is opened
                
                if (res) {
                    errorCount = 0
                    isConfirmationSuccessful = true
                } else {
                    isErrorState = true
                    supportingText = WRONG_CODE_TEXT
                    errorCount += 1
                }
                pinCode = ""
                isButtonEnabled = false
                isErrorLimitReached = errorCount >= ERROR_LIMIT
            } catch (e: Exception) {
            } finally {
                codeToCheck.fill(Char(0))
                isLoading = false
            }
        }
    }
}

private const val WRONG_CODE_TEXT = "Retype your code"
private const val INDICATION_CODE_TEXT = "Enter your PIN code"
private const val ERROR_LIMIT = 5
