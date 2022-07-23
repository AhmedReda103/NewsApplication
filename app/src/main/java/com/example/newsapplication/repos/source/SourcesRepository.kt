package com.example.newsapplication.repos.source

import com.example.newsapplication.model.SourcesItem

interface SourcesRepository {

    suspend fun getSources(category: String): List<SourcesItem?>?
}

interface SourcesOnlineDataSource {
    suspend fun getSources(category: String): List<SourcesItem?>?
}

interface SourcesOfflineDataSource {
    suspend fun updateSources(sources: List<SourcesItem?>?)

    suspend fun getSourcesByCategory(category: String): List<SourcesItem>
}