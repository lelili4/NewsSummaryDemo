package com.example.newssummary.data.datasource.dataSource

import android.content.Context
import com.example.newssummary.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDatasource {
    suspend fun getTopHeadlines(country:String, page:Int): Response<APIResponse>
    suspend fun getSearchedTopHealines(country:String,searchQuery:String, page:Int): Response<APIResponse>
    suspend fun getSummary(context: Context, url:String):String
}