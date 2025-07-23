package com.example.testone.data.repository

import com.example.testone.data.ApiService
import com.example.testone.data.model.ListItem
import com.example.testone.domain.repository.ListRepository
import com.example.testone.prasentation.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject
import kotlin.collections.sortedByDescending
import kotlin.collections.take

class ListRepositoryImpl @Inject constructor(val apiService: ApiService): ListRepository {

        override suspend fun getUserList(): Flow<UiState<List<ListItem>>> = flow {
            try {
                emit(UiState.Loading)
                val response = apiService.getUserList()
                if(response.isSuccessful) {
                    val data = response.body()

                    data?.let {

                       data.sortedByDescending {
                           it.name
                       }
                        val take = it.take(3)
                        emit(UiState.Success(take))
                    }
                    //emit()

                } else {
                    emit(UiState.Failure(""))
                }
            } catch (e: kotlin.Exception) {
                emit(UiState.Failure(e.message ?: "Unknown Exception"))
            }
        }.flowOn(Dispatchers.IO)
}