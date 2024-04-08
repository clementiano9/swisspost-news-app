package com.clement.data.fake

import com.clement.domain.model.Article
import com.clement.domain.model.Source


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