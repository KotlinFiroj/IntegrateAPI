package com.example.testone.domain.usecase

import com.example.testone.domain.repository.RegistrationRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(private val repository: RegistrationRepository) {
    suspend operator fun invoke(email: String): Boolean {
        if (!email.contains("@")) return false
        return repository.forgotPassword(email)
    }
}
