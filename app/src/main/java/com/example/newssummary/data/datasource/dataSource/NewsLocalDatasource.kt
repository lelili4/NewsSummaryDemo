package com.example.newssummary.data.datasource.dataSource

import com.example.newssummary.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDatasource {

    suspend fun saveNewstoDB(article: Article)
    fun getSavedArticles(): Flow<List<Article>?>
    suspend fun deleteArticlesFromDB(article: Article)
}