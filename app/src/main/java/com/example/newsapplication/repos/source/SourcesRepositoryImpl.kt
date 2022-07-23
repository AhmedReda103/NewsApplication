package com.example.newsapplication.repos.source

import com.example.newsapplication.NetworkHandler
import com.example.newsapplication.model.SourcesItem

class SourcesRepositoryImpl(
    val onlineDataSource: SourcesOnlineDataSource,
    val offlineDataSources: SourcesOfflineDataSource,
    val networkHandler: NetworkHandler

) : SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
            if (networkHandler.isOnline()) {
                val res = onlineDataSource.getSources(category)
                offlineDataSources.updateSources(res)
                return res
            }
            return offlineDataSources.getSourcesByCategory(category)
        } catch (ex: Exception) {
            // throw ex
            return offlineDataSources.getSourcesByCategory(category)
        }
    }
}