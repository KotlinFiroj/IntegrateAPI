package com.example.testone.domain.repository

import com.example.testone.domain.model.RegistrationForm
import com.example.testone.prasentation.view.register.RegisterState
import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {
    suspend fun saveRegistration(form: RegistrationForm): Flow<RegisterState>
    suspend fun forgotPassword(email: String): Boolean
}
