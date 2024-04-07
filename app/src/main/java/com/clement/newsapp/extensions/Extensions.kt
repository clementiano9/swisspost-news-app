package com.clement.newsapp.extensions

import android.os.Bundle
import com.clement.model.Article
import com.clement.model.Source

fun Article.toBundle(): Bundle {
    return Bundle().apply {
        putString("source", source.name)
        putString("sourceId", source.id)
        putString("author", author)
        putString("title", title)
        putString("description", description)
        putString("url", url)
        putString("urlToImage", urlToImage)
        putString("publishedAt", publishedAt)
        putString("content", content)
    }
}

fun Bundle.toArticle(): Article {
    return Article(
        source = Source(name = getString("source") ?: "", id = getString("sourceId") ?: ""),
        author = getString("author") ?: "",
        title = getString("title") ?: "",
        description = getString("description") ?: "",
        url = getString("url") ?: "",
        urlToImage = getString("urlToImage") ?: "",
        publishedAt = getString("publishedAt") ?: "",
        content = getString("content") ?: ""
    )
}