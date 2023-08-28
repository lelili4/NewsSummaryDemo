package com.example.newssummary.data.api

import com.example.newssummary.BuildConfig
import com.example.newssummary.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlindes(
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apikey:String = BuildConfig.API_KEY,
    ): Response<APIResponse>

    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlindes(
        @Query("country")
        country:String,
        @Query("q")
        searchQuery:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apikey:String = BuildConfig.API_KEY,
    ): Response<APIResponse>
}




