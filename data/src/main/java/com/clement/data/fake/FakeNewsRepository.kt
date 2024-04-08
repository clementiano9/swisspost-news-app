package com.clement.data.fake

import com.clement.domain.model.Article
import com.clement.domain.model.HeadlinesResponse
import com.clement.domain.model.Source
import com.clement.domain.repo.NewsRepository

/**
 * Fake implementation of [NewsRepository] to be used in tests
 */
class FakeNewsRepository: NewsRepository {
    var shouldReturnError = false
    var shouldReturnEmpty = false

    override suspend fun getHeadlines(): HeadlinesResponse {
        if (shouldReturnError)
            throw Exception("Error occurred")
        else if (shouldReturnEmpty)
            return HeadlinesResponse(articles = emptyList(), status = "ok", totalResults = 0)
        else
            return HeadlinesResponse(articles = results, status = "ok", totalResults = results.size)
    }
}


private val results = listOf(
    Article(
        author = "author",
        content = "content",
        description = "description",
        publishedAt = "publishedAt",
        source = Source(id = "sourceId", name = "source"),
        title = "title",
        url = "url",
        urlToImage = "urlToImage"
    ),
    Article(
        author = "author",
        content = "content",
        description = "description",
        publishedAt = "publishedAt",
        source = Source(id = "sourceId", name = "source"),
        title = "title",
        url = "url",
        urlToImage = "urlToImage"
    ),
)