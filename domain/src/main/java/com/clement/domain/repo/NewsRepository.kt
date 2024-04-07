package com.clement.domain.repo

import com.clement.model.HeadlinesResponse

interface NewsRepository {
    suspend fun getHeadlines(): HeadlinesResponse
}
