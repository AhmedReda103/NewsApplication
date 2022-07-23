package com.example.newsapplication.repos.news

import com.example.newsapplication.api.Constants
import com.example.newsapplication.api.WebServices
import com.example.newsapplication.model.ArticlesItem

class NewsOnlineDataSourceImpl(val webServices: WebServices) : NewsOnlineDataSource {

    override suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItem?>? {
        try {
            val result = webServices.getNews(Constants.apiKey, sourceId)
            return result.articles

        } catch (ex: Exception) {
            throw ex
        }
    }
}