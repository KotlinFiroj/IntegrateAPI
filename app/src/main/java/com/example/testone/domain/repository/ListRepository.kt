package com.example.testone.domain.repository

import com.example.testone.data.model.ListItem
import com.example.testone.prasentation.UiState
import kotlinx.coroutines.flow.Flow

interface ListRepository {

    suspend fun getUserList(): Flow<UiState<List<ListItem>>>

}