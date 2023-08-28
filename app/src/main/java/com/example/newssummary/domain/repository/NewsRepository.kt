package com.example.newssummary.domain.repository

import android.content.Context
import com.example.newssummary.data.model.APIResponse
import com.example.newssummary.data.model.Article
import com.example.newssummary.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadlines(country:String, page:Int): Resource<APIResponse>
    suspend fun getSearchedNews(country: String, searchQuery:String, page: Int):Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>?>
    suspend fun getSummary(context: Context, url:String):String
}