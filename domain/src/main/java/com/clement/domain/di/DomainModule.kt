package com.clement.domain.di

import com.clement.domain.usecase.GetHeadlinesUseCase
import com.clement.domain.repo.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class DomainModule {
    @Provides
    @Singleton
    fun provideGetHeadlinesUseCase(newsRepository: NewsRepository): GetHeadlinesUseCase {
        return GetHeadlinesUseCase(newsRepository)
    }
}