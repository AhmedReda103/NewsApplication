package com.example.newsapplication.repos.news

import com.example.newsapplication.model.ArticlesItem

interface NewsRepository {

    suspend fun getNews(sourceId: String): List<ArticlesItem?>?

}


interface NewsOnlineDataSource {

    suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItem?>?

}