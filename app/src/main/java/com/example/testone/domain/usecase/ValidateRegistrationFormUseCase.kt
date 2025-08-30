package com.example.testone.domain.usecase

import com.example.testone.domain.model.RegistrationForm
import javax.inject.Inject

class ValidateRegistrationFormUseCase @Inject constructor() {
    operator fun invoke(form: RegistrationForm): Boolean {
        return form.name.isNotBlank() &&
            form.email.contains("@") &&
            form.password.length >= 6
    }
}
