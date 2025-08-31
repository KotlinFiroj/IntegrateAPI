package com.example.testone.data.repository

import com.example.testone.data.remote.RegisterService
import com.example.testone.domain.model.RegistrationForm
import com.example.testone.domain.repository.RegistrationRepository
import com.example.testone.prasentation.view.register.RegisterState
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.net.SocketTimeoutException

class RegistrationRepositoryImpl @Inject constructor(val apiService: RegisterService) :
    RegistrationRepository {
    override suspend fun saveRegistration(form: RegistrationForm) = flow {
        emit(RegisterState.Loading)
        try {
            val res = apiService.register(form)
            if (res.isSuccessful) {
                val bodyData = res.body()
                emit(RegisterState.Success(bodyData!!))
            } else {
                emit(RegisterState.Error("UnSuccessful"))
            }
        } catch (e: SocketTimeoutException) {
            emit(RegisterState.Error("Error SocketTimeoutException " + e.message))
        } catch (e: IOException) {
            emit(RegisterState.Error("Error IOException " + e.message))
        } catch (e: Exception) {
            emit(RegisterState.Error("Error Exception " + e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun forgotPassword(email: String): Boolean {
        delay(1000) // simulate API call
        return email != "unknown@example.com" // pretend unknown email fails
    }
}
