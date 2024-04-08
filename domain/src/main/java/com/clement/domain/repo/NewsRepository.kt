package com.clement.domain.repo

import com.clement.domain.model.HeadlinesResponse

interface NewsRepository {
    suspend fun getHeadlines(): HeadlinesResponse
}
