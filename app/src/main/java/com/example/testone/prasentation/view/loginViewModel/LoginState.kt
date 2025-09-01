package com.example.testone.prasentation.view.loginViewModel

sealed class LoginState<out T> {

    object Idle : LoginState<Nothing>()
    object Loading : LoginState<Nothing>()
    data class Error(val error: LoginError) : LoginState<Nothing>()
    data class Success<T>(val response: T) : LoginState<T>()
}

sealed class LoginError {
    object Network : LoginError()
    object InvalidCredential : LoginError()
    object UnAuthorize : LoginError()
    object Forbidden : LoginError()
    object NotFound : LoginError()
    object Server : LoginError()
    data class UnKnown(val code: Int, val message: String? = null) : LoginError()
}
