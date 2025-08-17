package com.example.testone

import com.example.testone.domain.model.UserUI
import com.example.testone.domain.usecase.ListUseCause
import com.example.testone.prasentation.UiState
import com.example.testone.prasentation.viewModel.ListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class ListViewModelTest {


    lateinit var  useCause: ListUseCause
    lateinit var viewModel: ListViewModel
    val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        useCause = mockk()
    }

    @Test
    fun `fetch result and return list`() = runTest {
        val list = listOf(UserUI("Name"))
        val result = MutableStateFlow(UiState.Success(list))
        coEvery { useCause() } returns result
        viewModel = ListViewModel(useCause)
        viewModel.getUserList()
        advanceUntilIdle()
        val state = viewModel.list.first()
        assertTrue(state is UiState.Success)
        val stateRes = state as UiState.Success
        assertEquals(result.value.list, stateRes.list)
    }

    @Test
    fun `fetch return empty`() = runTest {
        val mockList = MutableStateFlow(UiState.Success(emptyList()))
        coEvery { useCause() } returns mockList
        viewModel = ListViewModel(useCause)
        viewModel.getUserList()
        advanceUntilIdle()
        val state = viewModel.list.first()
        assertTrue(state is UiState.Success)
        val stateRes = state as UiState.Success
        assertEquals(mockList.value.list, stateRes.list)
    }

    @Test
    fun `fetch return error`() = runTest{
        val mockError = MutableStateFlow(UiState.Failure("Error"))
        coEvery { useCause() } returns mockError

        viewModel = ListViewModel(useCause)
        viewModel.getUserList()
        advanceUntilIdle()

        val state = viewModel.list.first()
        assertTrue(state is UiState.Failure)
        val stateRes = state as UiState.Failure
        assertEquals(mockError.value.message, stateRes.message)



    }

}