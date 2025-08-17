package com.example.testone.domain.usecase

import com.example.testone.domain.model.RegistrationForm
import com.example.testone.domain.repository.RegistrationRepository
import javax.inject.Inject

class SaveRegistrationUseCase @Inject constructor(private val repository: RegistrationRepository) {
    suspend operator fun invoke(form: RegistrationForm): Boolean {
        return repository.saveRegistration(form)
    }
}
