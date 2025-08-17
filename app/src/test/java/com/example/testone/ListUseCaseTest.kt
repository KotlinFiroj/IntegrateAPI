package com.example.testone

import com.example.testone.domain.model.UserUI
import com.example.testone.domain.repository.ListRepository
import com.example.testone.domain.usecase.ListUseCause
import com.example.testone.prasentation.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ListUseCaseTest {

    private lateinit var repository: ListRepository
    private lateinit var useCase: ListUseCause

    @Before
    fun setUp() {
        repository = mockk()
        useCase = ListUseCause(repository)
    }

    @Test
    fun `getList returns expected result`() = runTest {
        // Arrange
        val list = listOf(UserUI(name = "Name"))
        val expectedList = MutableStateFlow(UiState.Success(list))
        coEvery { repository.getUserList() } returns expectedList

        // Act
        val result = useCase()

        val state = result.first()
        // Assert
        assertTrue(state is UiState.Success)
        val stateRes = state as UiState.Success
        assertEquals(expectedList.value.list, stateRes.list)
    }
}
