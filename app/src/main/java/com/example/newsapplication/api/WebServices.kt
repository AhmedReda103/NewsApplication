package com.example.newsapplication.api

import android.app.DownloadManager
import com.example.newsapplication.model.NewsResponse
import com.example.newsapplication.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey") api: String,
        @Query("category") category: String

    ): Call<SourcesResponse>


    @GET("v2/everything")
    fun getNews(
        @Query("apiKey") api: String, @Query("sources") sources: String

    ): Call<NewsResponse>

}