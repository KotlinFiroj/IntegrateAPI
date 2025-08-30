package com.example.testone.prasentation.view.register

import com.example.testone.domain.model.RegisterStatus

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val message: RegisterStatus) : RegisterState()
    data class Error(val error: String) : RegisterState()
}
