package com.example.newssummary.presentation.di

import com.example.newssummary.data.datasource.dataSource.NewsLocalDatasource
import com.example.newssummary.domain.repository.NewsRepositoryImpl
import com.example.newssummary.data.datasource.dataSource.NewsRemoteDatasource
import com.example.newssummary.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDatasource: NewsRemoteDatasource,
        newsLocalDatasource: NewsLocalDatasource
    ):NewsRepository{
        return NewsRepositoryImpl(newsRemoteDatasource, newsLocalDatasource)
    }


}