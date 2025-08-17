package com.example.testone.data.repository

import com.example.testone.data.remote.ApiService
import com.example.testone.domain.repository.ListRepository
import com.example.testone.prasentation.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(val apiService: ApiService) : ListRepository {

    override suspend fun getUserList(): Flow<UiState> = flow {
        try {
            emit(UiState.Loading)
            val response = apiService.getUserList()
            if (response.isSuccessful) {
                val data = response.body()
                    /*val res = data?.let { it.map { it.toUser() }
                      //  .sortedBy { it.name }.take(3)
                    }*/
                emit(UiState.Success(emptyList()))
            } else {
                emit(UiState.Failure(""))
            }
        } catch (e: SocketTimeoutException) {
            emit(UiState.Failure(e.message ?: "Unknown Exception"))
        } catch (e: IOException) {
            emit(UiState.Failure(e.message ?: "Unknown Exception"))
        } catch (e: Exception) {
            emit(UiState.Failure(e.message ?: "Unknown Exception"))
        }
    }.flowOn(Dispatchers.IO)
}
