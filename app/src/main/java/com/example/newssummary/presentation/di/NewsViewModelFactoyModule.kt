package com.example.newssummary.presentation.di

import android.app.Application
import com.example.newssummary.domain.usecase.*
import com.example.newssummary.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsViewModelFactoyModule {
    @Singleton
    @Provides
    fun provideNewViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        saveNewsUseCase: SaveNewsUseCase,
        getSavedNewsUseCase: GetSavedNewsUseCase,
        deleteSavedNewsUseCase: DeleteSavedNewsUseCase,
        getSummaryUseCase: GetSummaryUseCase
    ):NewsViewModelFactory{
        return NewsViewModelFactory(
            application, getNewsHeadlinesUseCase, getSearchedNewsUseCase,
            saveNewsUseCase,
            getSavedNewsUseCase,
            deleteSavedNewsUseCase,
            getSummaryUseCase
        )
    }
}