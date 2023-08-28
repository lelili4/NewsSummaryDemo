package com.example.newssummary.domain.usecase

import com.example.newssummary.data.model.Article
import com.example.newssummary.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(): Flow<List<Article>?>{
        return newsRepository.getSavedNews()
    }
}