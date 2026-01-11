package fr.ailurus.housepassp2p.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import fr.ailurus.housepassp2p.Constants

class SetupViewModel : ViewModel() {

    var pinCode by mutableStateOf("")
        private set

    var confirmCode by mutableStateOf("")
        private set

    var isErrorState by mutableStateOf(false)
        private set

    var isButtonEnabled by mutableStateOf(false)
        private set

    var codeSupportText: String by mutableStateOf(IndicationCodeText)
        private set

    var confirmSupportText: String by mutableStateOf(IndicationConfirmText)
        private set

    fun onCodeChange(newCode: String) {
        // Prevent typing more than the limit
        if (newCode.length <= Constants.CODE_CHAR_COUNT) {
            pinCode = newCode
            validateInput()
        }
    }

    fun onConfirmChange(newConfirm: String) {
        // Prevent typing more than the limit
        if (newConfirm.length <= Constants.CODE_CHAR_COUNT) {
            confirmCode = newConfirm
            validateInput()
        }
    }

    fun onConfirm() {
        pinCode = ""
        confirmCode = ""
    }

    /**
     * Centralized validation logic.
     * Checks if both codes are full and identical to enable the button.
     * Updates error state only if confirmation is full but mismatching.
     */
    private fun validateInput() {
        val isPinFull = pinCode.length == Constants.CODE_CHAR_COUNT
        val isConfirmFull = confirmCode.length == Constants.CODE_CHAR_COUNT
        val areCodesMatching = pinCode == confirmCode

        // Button is enabled ONLY if both are full and identical
        isButtonEnabled = isPinFull && isConfirmFull && areCodesMatching

        // Error logic: Only show error if user finished typing confirm code and it's wrong
        if (isConfirmFull && !areCodesMatching) {
            isErrorState = true
            confirmSupportText = ErrorMatchText
        } else {
            isErrorState = false
            confirmSupportText = IndicationConfirmText
        }
    }

    companion object {
        val setupViewModelFactory = viewModelFactory {
            initializer {
                SetupViewModel()
            }
        }
    }
}

private const val IndicationCodeText = "Enter your PIN code"
private const val IndicationConfirmText = "Confirm your PIN code"
private const val ErrorMatchText = "Codes must match"