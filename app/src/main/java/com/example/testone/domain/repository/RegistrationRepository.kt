package com.example.testone.domain.repository

import com.example.testone.domain.model.RegistrationForm

interface RegistrationRepository {
    suspend fun saveRegistration(form: RegistrationForm): Boolean
    suspend fun forgotPassword(email: String): Boolean
}
