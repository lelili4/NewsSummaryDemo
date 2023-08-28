package com.example.newssummary.domain.repository

import android.content.Context
import com.example.newssummary.data.datasource.dataSource.NewsLocalDatasource
import com.example.newssummary.data.model.APIResponse
import com.example.newssummary.data.model.Article
import com.example.newssummary.data.datasource.dataSource.NewsRemoteDatasource
import com.example.newssummary.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDatasource: NewsRemoteDatasource,
    private val newsLocalDatasource: NewsLocalDatasource
):NewsRepository {
    override suspend fun getNewsHeadlines(country:String, page:Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDatasource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return responseToResource(
            newsRemoteDatasource.getSearchedTopHealines(country, searchQuery, page)
        )
    }

    private fun responseToResource(response: Response<APIResponse>):Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let{result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    override suspend fun saveNews(article: Article) {
        return newsLocalDatasource.saveNewstoDB(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDatasource.deleteArticlesFromDB(article)
    }

    override fun getSavedNews(): Flow<List<Article>?> {
        return newsLocalDatasource.getSavedArticles()
    }

    override suspend fun getSummary(context: Context, url: String):String {
        return newsRemoteDatasource.getSummary(context, url)
    }
}