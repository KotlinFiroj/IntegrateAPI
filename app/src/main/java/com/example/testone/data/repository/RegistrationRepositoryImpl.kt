package com.example.testone.data.repository

import com.example.testone.domain.model.RegistrationForm
import com.example.testone.domain.repository.RegistrationRepository
import jakarta.inject.Inject
import kotlinx.coroutines.delay

class RegistrationRepositoryImpl @Inject constructor() : RegistrationRepository {
    override suspend fun saveRegistration(form: RegistrationForm): Boolean {
        delay(1000) // simulate API call
        return form.username != "fail" // pretend "fail" is invalid
    }

    override suspend fun forgotPassword(email: String): Boolean {
        delay(1000) // simulate API call
        return email != "unknown@example.com" // pretend unknown email fails
    }
}
