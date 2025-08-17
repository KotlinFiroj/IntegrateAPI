package com.example.testone.domain.usecase

import com.example.testone.data.repository.ListRepositoryImpl
import com.example.testone.prasentation.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListUseCause @Inject constructor(val listRepositoryImpl: ListRepositoryImpl) {

    suspend operator fun invoke(): Flow<UiState> {
        val data = listRepositoryImpl.getUserList()

        return listRepositoryImpl.getUserList()
    }
}
