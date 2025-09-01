package com.example.testone.prasentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testone.domain.model.RegistrationForm
import com.example.testone.domain.usecase.ForgotPasswordUseCase
import com.example.testone.domain.usecase.SaveRegistrationUseCase
import com.example.testone.domain.usecase.ValidateRegistrationFormUseCase
import com.example.testone.prasentation.view.register.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateForm: ValidateRegistrationFormUseCase,
    private val saveRegistration: SaveRegistrationUseCase,
    private val forgotPassword: ForgotPasswordUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val uiState: StateFlow<RegisterState> = _uiState

    private val _currentForm = MutableStateFlow(RegistrationForm("", "", ""))
    var currentForm: StateFlow<RegistrationForm> = _currentForm

    fun onUsernameChanged(username: String) {
        _currentForm.value = _currentForm.value.copy(name = username)
    }

    fun onEmailChanged(email: String) {
        _currentForm.value = _currentForm.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _currentForm.value = _currentForm.value.copy(password = password)
    }

    fun isFormValid(): Boolean = validateForm(currentForm.value)

    fun onSaveClicked() {
        viewModelScope.launch {
            saveRegistration(currentForm.value).collect {
                _uiState.value = it
            }
        }
    }

    fun onForgotPasswordClicked() {
        viewModelScope.launch {
            _uiState.value = RegisterState.Loading
            val success = forgotPassword(currentForm.value.email)
            /*_uiState.value = if (success) {
                RegistrationUiState.ForgotPasswordSuccess("Password reset link sent!")
            } else {
                RegistrationUiState.ForgotPasswordError("Email not found.")
            }*/
        }
    }

    fun onUserLogin() {
        _uiState.value = RegisterState.Login
    }
}
