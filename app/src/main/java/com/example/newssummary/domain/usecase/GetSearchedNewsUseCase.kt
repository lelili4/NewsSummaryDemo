package com.example.newssummary.domain.usecase

import android.app.DownloadManager.Query
import com.example.newssummary.data.model.APIResponse
import com.example.newssummary.data.util.Resource
import com.example.newssummary.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country:String, searchQuery: String, page:Int): Resource<APIResponse>{
        return newsRepository.getSearchedNews(country, searchQuery, page)
    }
}
//f8ace8bc26ee4a0d86579ca629ad5a1a