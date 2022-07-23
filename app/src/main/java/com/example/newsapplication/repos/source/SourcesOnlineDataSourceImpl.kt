package com.example.newsapplication.repos.source

import com.example.newsapplication.api.Constants
import com.example.newsapplication.api.WebServices
import com.example.newsapplication.model.SourcesItem

class SourcesOnlineDataSourceImpl(val webServices: WebServices) : SourcesOnlineDataSource {

    override suspend fun getSources(category: String): List<SourcesItem?>? {

        try {

            val res = webServices.getSources(Constants.apiKey, category)
            return res.sources

        } catch (ex: Exception) {
            throw ex
        }

    }

}