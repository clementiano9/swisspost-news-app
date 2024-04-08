package com.clement.domain.model

data class HeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String
)

data class Source (
    val id: String?,
    val name: String
)

data class ApiError(
    val status: String,
    val code: String,
    val message: String
)