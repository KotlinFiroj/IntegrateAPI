package com.example.testone.domain.usecase

import com.example.testone.domain.model.RegistrationForm
import com.example.testone.domain.repository.RegistrationRepository
import com.example.testone.prasentation.view.register.RegisterState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveRegistrationUseCase @Inject constructor(private val repository: RegistrationRepository) {
    suspend operator fun invoke(form: RegistrationForm): Flow<RegisterState> {
        return repository.saveRegistration(form)
    }
}
