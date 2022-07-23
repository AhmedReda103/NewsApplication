package com.example.newsapplication.repos.source

import com.example.newsapplication.database.MyDatabase
import com.example.newsapplication.model.SourcesItem

class SourcesOfflineDataSourceImpl(val myDataBase: MyDatabase) : SourcesOfflineDataSource {
    override suspend fun updateSources(sources: List<SourcesItem?>?) {
        myDataBase.SourceDao().updateSources(sources)
    }

    override suspend fun getSourcesByCategory(category: String): List<SourcesItem> {
        return myDataBase.SourceDao().getSourcesByCategoryId(category)
    }
}