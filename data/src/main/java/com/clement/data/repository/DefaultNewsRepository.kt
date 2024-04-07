package com.clement.data.repository

import com.clement.data.ApiInterface
import com.clement.data.BuildConfig
import com.clement.domain.repo.NewsRepository
import com.clement.model.HeadlinesResponse
import javax.inject.Inject

class DefaultNewsRepository @Inject constructor(
    private val apiInterface: ApiInterface,

) : NewsRepository {
    private val apiKey: String = BuildConfig.API_KEY
    override suspend fun getHeadlines(): HeadlinesResponse {
        return apiInterface.getHeadlines(apiKey)
    }
}