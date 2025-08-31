package com.example.testone.prasentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testone.prasentation.viewModel.ListViewModel

@Composable
fun LoadUserList(viewModel: ListViewModel = hiltViewModel()) {
    val list by viewModel.list.collectAsState()
    when (list) {
        is UiState.Loading -> {
        }
        is UiState.Success -> {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items((list as UiState.Success).list) { item ->
                    Text(item.name)
                }
            }
        }
        is UiState.Failure -> {
        }

        UiState.Idle -> {
        }
    }
}
