package com.clement.newsapp.feature.headlines.viewmodel

import com.clement.domain.usecase.GetHeadlinesUseCase
import com.clement.newsapp.feature.headlines.healines.HeadlinesViewModel
import com.clement.data.fake.FakeNewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HeadlinesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val repository = FakeNewsRepository()
    private val useCase = GetHeadlinesUseCase(repository)
    private lateinit var sut: HeadlinesViewModel

    @Before
    fun setup () {
        sut = HeadlinesViewModel(useCase, testDispatcher)
    }

    @Test
    fun test_loading_on_init() {

        assertTrue(sut.viewState.value.isLoading)
    }

    @Test
    fun test_fetch_headlines_loading_complete() {
        sut.getHeadlines()

        testDispatcher.scheduler.advanceUntilIdle()

        assertFalse(sut.viewState.value.isLoading)
    }

    @Test
    fun test_fetch_headlines_success() {
        sut.getHeadlines()
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(sut.viewState.value.articles.isNotEmpty())
    }

    @Test
    fun test_fetch_headlines_error() {
        repository.shouldReturnError = true
        sut.getHeadlines()

        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(sut.viewState.value.error != null)
    }

    @Test
    fun test_fetch_headlines_empty() {
        repository.shouldReturnEmpty = true
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(sut.viewState.value.articles.isEmpty())
    }

}