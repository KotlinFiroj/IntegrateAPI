package com.example.testone.domain.repository

import com.example.testone.data.dto.login.LoginResponse
import com.example.testone.domain.model.LoginForm
import com.example.testone.prasentation.view.loginViewModel.LoginState
import kotlinx.coroutines.flow.Flow

interface LoginRepo {

    suspend fun login(loginForm: LoginForm): Flow<LoginState<LoginResponse>>
}
