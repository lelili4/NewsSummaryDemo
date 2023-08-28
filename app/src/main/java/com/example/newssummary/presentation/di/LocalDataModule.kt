package com.example.newssummary.presentation.di

import com.example.newssummary.data.datasource.dataSource.NewsLocalDatasource
import com.example.newssummary.data.datasource.dataSourceImpl.NewsLocalDataSourceImpl
import com.example.newssummary.data.db.ArticleDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(articleDAO: ArticleDAO):NewsLocalDatasource{
        return NewsLocalDataSourceImpl(articleDAO)
    }
}