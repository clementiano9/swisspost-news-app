package com.clement.data

import com.clement.model.HeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String = "us"
    ): HeadlinesResponse
}