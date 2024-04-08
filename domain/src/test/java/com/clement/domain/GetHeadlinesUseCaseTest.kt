package com.clement.domain

import com.clement.domain.repo.fake.FakeNewsRepository
import com.clement.domain.usecase.GetHeadlinesUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetHeadlinesUseCaseTest {

    private val repository = FakeNewsRepository()
    private lateinit var useCase: GetHeadlinesUseCase

    @Before
    fun setup() {
        useCase = GetHeadlinesUseCase(repository)
    }

    @Test
    fun `test invoke should return articles sorted date`() = runBlocking {
        val articles = useCase()
        val sortedArticles = repository.getHeadlines().articles.sortedByDescending {
            it.publishedAt
        }
        assertEquals(sortedArticles, articles)
    }
}