package com.example.testone.prasentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testone.domain.model.RegistrationForm
import com.example.testone.domain.usecase.ForgotPasswordUseCase
import com.example.testone.domain.usecase.SaveRegistrationUseCase
import com.example.testone.domain.usecase.ValidateRegistrationFormUseCase
import com.example.testone.prasentation.view.register.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val validateForm: ValidateRegistrationFormUseCase,
    private val saveRegistration: SaveRegistrationUseCase,
    private val forgotPassword: ForgotPasswordUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val uiState: StateFlow<RegisterState> = _uiState

    private var currentForm = RegistrationForm("Five", "Five@gmail.com", "Five@1234")

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
            saveRegistration(currentForm).collect {
                _uiState.value = it
            }
        }
    }

    fun onForgotPasswordClicked() {
        viewModelScope.launch {
            _uiState.value = RegisterState.Loading
            val success = forgotPassword(currentForm.email)
            /*_uiState.value = if (success) {
                RegistrationUiState.ForgotPasswordSuccess("Password reset link sent!")
            } else {
                RegistrationUiState.ForgotPasswordError("Email not found.")
            }*/
        }
    }
}
