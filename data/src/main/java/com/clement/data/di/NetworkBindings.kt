package com.clement.data.di

import com.clement.data.repository.DefaultNewsRepository
import com.clement.domain.repo.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkBindings {

    @Binds
    abstract fun bindsNewsRepository(defaultNewsRepository: DefaultNewsRepository): NewsRepository
}