package com.example.testone.domain.usecase

import com.example.testone.domain.repository.ListRepository
import com.example.testone.prasentation.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListUseCause @Inject constructor(val listRepositoryImpl: ListRepository) {

    suspend operator fun invoke(): Flow<UiState> {
        return listRepositoryImpl.getUserList()
    }
}
