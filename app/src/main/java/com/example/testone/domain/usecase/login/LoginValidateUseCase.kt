package com.example.testone.domain.usecase.login

import com.example.testone.domain.model.LoginForm
import jakarta.inject.Inject

class LoginValidateUseCase @Inject constructor() {

    operator fun invoke(loginForm: LoginForm): Boolean {
        return loginForm.username.isNotBlank() &&
            loginForm.password.length >= 6
    }
}
