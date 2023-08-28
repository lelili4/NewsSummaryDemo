package com.example.newssummary.data.datasource.dataSourceImpl

import com.example.newssummary.data.db.ArticleDAO
import com.example.newssummary.data.model.Article
import com.example.newssummary.data.datasource.dataSource.NewsLocalDatasource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articalDao: ArticleDAO
): NewsLocalDatasource {
    override suspend fun saveNewstoDB(article: Article) {
        articalDao.insert(article)
    }

    override fun getSavedArticles(): Flow<List<Article>?> {
        return articalDao.getAllArticles()
    }

    override suspend fun deleteArticlesFromDB(article: Article) {
        articalDao.deleteArticle(article)
    }
}