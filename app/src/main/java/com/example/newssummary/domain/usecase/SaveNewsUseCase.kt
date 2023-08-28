package com.example.newssummary.domain.usecase

import com.example.newssummary.data.model.Article
import com.example.newssummary.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.saveNews(article)

}