package com.example.testone.prasentation

sealed class RegistrationUiState {
    object Idle : RegistrationUiState()
    object Loading : RegistrationUiState()

    data class RegistrationSuccess(val message: String) : RegistrationUiState()
    data class RegistrationError(val error: String) : RegistrationUiState()

    data class ForgotPasswordSuccess(val message: String) : RegistrationUiState()
    data class ForgotPasswordError(val error: String) : RegistrationUiState()
}
