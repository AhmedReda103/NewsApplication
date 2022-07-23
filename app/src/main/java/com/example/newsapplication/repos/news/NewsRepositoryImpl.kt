package com.example.newsapplication.repos.news

import com.example.newsapplication.model.ArticlesItem
import java.lang.Exception

class NewsRepositoryImpl(val newsOnlineDataSource: NewsOnlineDataSource) : NewsRepository {


    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {

        try {
            val result = newsOnlineDataSource.getNewsBySourceId(sourceId)
            return result
        } catch (ex: Exception) {
            throw ex
        }
    }
}