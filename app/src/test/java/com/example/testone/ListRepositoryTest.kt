package com.example.testone

import app.cash.turbine.test
import com.example.testone.data.dto.ListItem
import com.example.testone.data.remote.ApiService
import com.example.testone.data.repository.ListRepositoryImpl
import com.example.testone.prasentation.UiState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ListRepositoryTest {

    lateinit var listRepositoryImpl: ListRepositoryImpl
    lateinit var apiService: ApiService

    @Before
    fun setup() {
        apiService = mockk()
        listRepositoryImpl = ListRepositoryImpl(apiService)
    }

    @Test
    fun `fetch and loading`() = runTest {
        coEvery { apiService.getUserList() } returns Response.success(emptyList())
        val result = listRepositoryImpl.getUserList()
        advanceUntilIdle()
        val state = result.first()
        assertTrue(state is UiState.Loading)
    }

    @Test
    fun `fetch and success`() = runTest {
        // Given
        val data = ListItem(name = "Name" )
        val fakeList = listOf(data)
        coEvery { apiService.getUserList() } returns Response.success(fakeList)

        // When + Then
        /*val result = listRepositoryImpl.getUserList()
        advanceUntilIdle()
        assertTrue(result.first() is UiState.Loading)
        */

        listRepositoryImpl.getUserList().test {
            // first emission
            assertTrue(awaitItem() is UiState.Loading)
            // second emission
            val success = awaitItem()
            assertTrue(success is UiState.Success)
            assertEquals("Name", (success as UiState.Success).list.first().name)
            awaitComplete()
        }

    }

    @Test
    fun `fetch and error`() = runTest {
        val mockRes = RuntimeException("Error")
        coEvery { apiService.getUserList() } throws mockRes

        listRepositoryImpl.getUserList().test {
            //First emit
            assertTrue(awaitItem() is UiState.Loading)
            //Second emit
            assertTrue(awaitItem() is UiState.Failure)
            awaitComplete()
        }
    }
}