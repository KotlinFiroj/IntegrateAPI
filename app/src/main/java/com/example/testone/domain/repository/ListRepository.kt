package com.example.testone.domain.repository

import com.example.testone.prasentation.view.UiState
import kotlinx.coroutines.flow.Flow

interface ListRepository {

    suspend fun getUserList(): Flow<UiState>
}
