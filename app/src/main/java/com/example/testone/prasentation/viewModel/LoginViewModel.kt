package com.example.testone.prasentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testone.data.dto.login.LoginResponse
import com.example.testone.domain.model.LoginForm
import com.example.testone.domain.usecase.login.LoginUseCase
import com.example.testone.domain.usecase.login.LoginValidateUseCase
import com.example.testone.prasentation.view.loginViewModel.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase,
    val loginValidateUseCase: LoginValidateUseCase,
) : ViewModel() {

    private val _loginResponse = MutableStateFlow<LoginState<LoginResponse>>(LoginState.Idle)
    val loginResponse = _loginResponse.asStateFlow()

    private val _loginForm = MutableStateFlow(LoginForm())
    val loginForm = _loginForm.asStateFlow()

    fun onUserNameChange(username: String) {
        _loginForm.value = _loginForm.value.copy(username = username)
    }

    fun onPasswordChange(password: String) {
        _loginForm.value = _loginForm.value.copy(password = password)
    }

    fun isValidForm(): Boolean {
        return loginValidateUseCase(loginForm.value)
    }

    fun login() {
        viewModelScope.launch {
            loginUseCase(loginForm.value).collect {
                _loginResponse.value = it
            }
        }
    }
}
