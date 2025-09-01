package com.example.testone.data.repository

import com.example.testone.data.dto.login.LoginResponse
import com.example.testone.data.remote.RegisterService
import com.example.testone.domain.model.LoginForm
import com.example.testone.domain.repository.LoginRepo
import com.example.testone.prasentation.view.loginViewModel.LoginError
import com.example.testone.prasentation.view.loginViewModel.LoginState
import com.example.testone.utils.safeFlow
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class LoginRepoImpl @Inject constructor(val registerService: RegisterService) : LoginRepo {
    override suspend fun login(loginForm: LoginForm): Flow<LoginState<LoginResponse>> =

        safeFlow(
            call = { registerService.login(loginForm) },
            mapError = { code, body ->
                when (code) {
                    400 -> LoginError.InvalidCredential
                    else -> LoginError.UnKnown(code, body)
                }
            },
        )
}
