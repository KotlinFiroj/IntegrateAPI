package com.example.testone.domain.usecase.login

import com.example.testone.data.dto.login.LoginResponse
import com.example.testone.domain.model.LoginForm
import com.example.testone.domain.repository.LoginRepo
import com.example.testone.prasentation.view.loginViewModel.LoginState
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class LoginUseCase @Inject constructor(val loginRepo: LoginRepo) {

    suspend operator fun invoke(loginForm: LoginForm): Flow<LoginState<LoginResponse>> {
        return loginRepo.login(loginForm)
    }
}
