package com.example.testone.utils

import com.example.testone.prasentation.view.loginViewModel.LoginError
import com.example.testone.prasentation.view.loginViewModel.LoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

inline fun <T> safeFlow(
    crossinline call: suspend () -> Response<T>,
    crossinline mapError: (code: Int, errorBody: String?) -> LoginError = { code, body ->
        when (code) {
            400 -> LoginError.InvalidCredential
            401 -> LoginError.UnAuthorize
            403 -> LoginError.Forbidden
            404 -> LoginError.NotFound
            500 -> LoginError.Server
            else -> LoginError.UnKnown(code, body)
        }
    },
): Flow<LoginState<T>> = flow {
    emit(LoginState.Loading)
    try {
        val response = call()
        if (response.isSuccessful) {
            response.body()?.let { body ->
                emit(LoginState.Success(body))
            }
                ?: emit(LoginState.Error(LoginError.UnKnown(response.code(), "Empty body")))
        } else {
            emit(LoginState.Error(mapError(response.code(), response.errorBody()?.string())))
        }
    } catch (e: Exception) {
        emit(LoginState.Error(error = LoginError.Network))
    }
}.flowOn(Dispatchers.IO)
