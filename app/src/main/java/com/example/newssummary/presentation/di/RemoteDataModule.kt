package com.example.newssummary.presentation.di

import com.example.newssummary.data.api.NewsAPIService
import com.example.newssummary.data.datasource.dataSource.NewsRemoteDatasource
import com.example.newssummary.data.datasource.dataSourceImpl.NewsRemoteDateSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsAPIService: NewsAPIService):NewsRemoteDatasource{
        return NewsRemoteDateSourceImpl(newsAPIService)
    }
}