package com.example.testone.prasentation

sealed class UiState<out T> {

    object Loading: UiState<Nothing>()
    data class Success<T>(val list: T): UiState<T>()
    data class Failure(val message: String): UiState<Nothing>()

}