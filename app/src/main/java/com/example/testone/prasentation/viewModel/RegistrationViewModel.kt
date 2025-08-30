package com.example.testone.prasentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testone.domain.model.RegistrationForm
import com.example.testone.domain.usecase.ForgotPasswordUseCase
import com.example.testone.domain.usecase.SaveRegistrationUseCase
import com.example.testone.domain.usecase.ValidateRegistrationFormUseCase
import com.example.testone.prasentation.RegistrationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val validateForm: ValidateRegistrationFormUseCase,
    private val saveRegistration: SaveRegistrationUseCase,
    private val forgotPassword: ForgotPasswordUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegistrationUiState>(RegistrationUiState.Idle)
    val uiState: StateFlow<RegistrationUiState> = _uiState

    private var currentForm = RegistrationForm("", "", "")

    fun onUsernameChanged(username: String) {
        currentForm = currentForm.copy(name = username)
    }

    fun onEmailChanged(email: String) {
        currentForm = currentForm.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        currentForm = currentForm.copy(password = password)
    }

    fun isFormValid(): Boolean = validateForm(currentForm)

    fun onSaveClicked() {
        viewModelScope.launch {
            _uiState.value = RegistrationUiState.Loading
            val success = saveRegistration(currentForm)
            _uiState.value = if (success) {
                RegistrationUiState.RegistrationSuccess("Registration successful!")
            } else {
                RegistrationUiState.RegistrationError("Registration failed.")
            }
        }
    }

    fun onForgotPasswordClicked() {
        viewModelScope.launch {
            _uiState.value = RegistrationUiState.Loading
            val success = forgotPassword(currentForm.email)
            _uiState.value = if (success) {
                RegistrationUiState.ForgotPasswordSuccess("Password reset link sent!")
            } else {
                RegistrationUiState.ForgotPasswordError("Email not found.")
            }
        }
    }
}
