package com.clement.domain.usecase

import com.clement.domain.repo.NewsRepository
import javax.inject.Inject

class GetHeadlinesUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke() = repository.getHeadlines().articles
}