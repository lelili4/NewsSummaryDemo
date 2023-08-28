package com.example.newssummary.domain.usecase

import com.example.newssummary.data.model.Article
import com.example.newssummary.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}