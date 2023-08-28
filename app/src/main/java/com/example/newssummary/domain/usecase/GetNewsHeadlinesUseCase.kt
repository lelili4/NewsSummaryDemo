package com.example.newssummary.domain.usecase

import com.example.newssummary.data.model.APIResponse
import com.example.newssummary.data.util.Resource
import com.example.newssummary.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(country:String, page:Int):Resource<APIResponse>{
        return newsRepository.getNewsHeadlines(country,page)
    }
}