package com.clement.newsapp

import com.clement.domain.usecase.GetHeadlinesUseCase
import com.clement.newsapp.fake.FakeNewsRepository
import com.clement.newsapp.ui.healines.HeadlinesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HeadlinesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val useCase = GetHeadlinesUseCase(FakeNewsRepository())

    @Test
    fun test_loading_on_init() {
        // Given
        val sut = HeadlinesViewModel(useCase, testDispatcher)

        // Then
        assertTrue(sut.viewState.value.isLoading)
    }

    @Test
    fun test_fetch_headlines_loading_complete() {
        // Given
        val sut = HeadlinesViewModel(useCase, testDispatcher)
        // When
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertFalse(sut.viewState.value.isLoading)
    }

    @Test
    fun test_fetch_headlines_success() {
        // Given
        val sut = HeadlinesViewModel(useCase, testDispatcher)
        // When
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(sut.viewState.value.articles.isNotEmpty())
    }

    @Test
    fun test_fetch_headlines_error() {
        // Given
        val useCase = GetHeadlinesUseCase(FakeNewsRepository().apply { shouldReturnError = true })
        val sut = HeadlinesViewModel(useCase, testDispatcher)
        // When
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(sut.viewState.value.error != null)
    }

    @Test
    fun test_fetch_headlines_empty() {
        // Given
        val useCase = GetHeadlinesUseCase(FakeNewsRepository().apply { shouldReturnEmpty = true })
        val sut = HeadlinesViewModel(useCase, testDispatcher)
        // When
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(sut.viewState.value.articles.isEmpty())
    }

}