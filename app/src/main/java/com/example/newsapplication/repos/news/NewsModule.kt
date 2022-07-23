package com.example.newsapplication.repos.news

import com.example.newsapplication.api.WebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class NewsModule {

    @Provides
    fun provideNewsRepo(newsOnlineDataSource: NewsOnlineDataSource): NewsRepository {
        return NewsRepositoryImpl(newsOnlineDataSource)
    }

    @Provides
    fun provideNewsOnlineDataSource(webServices: WebServices): NewsOnlineDataSource {
        return NewsOnlineDataSourceImpl(webServices)
    }

}