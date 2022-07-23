package com.example.newsapplication.repos.source

import com.example.newsapplication.NetworkHandler
import com.example.newsapplication.NetworkHandlerImpl
import com.example.newsapplication.api.WebServices
import com.example.newsapplication.database.MyDatabase
import com.example.newsapplication.repos.source.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

    @Provides
    fun provideOnlineDataSource(webServices: WebServices)
            : SourcesOnlineDataSource {
        return SourcesOnlineDataSourceImpl(webServices)
    }

    @Provides
    fun provideOfflineDataSource(database: MyDatabase): SourcesOfflineDataSource {
        return SourcesOfflineDataSourceImpl(database)
    }

    @Singleton
    @Provides
    fun provideDataBase(): MyDatabase {
        return MyDatabase.getInstance()
    }

    @Provides
    fun provideSourcesRepo(
        onlineDataSource: SourcesOnlineDataSource,
        offlineDataSource: SourcesOfflineDataSource,
        networkHandler: NetworkHandler
    ): SourcesRepository {
        return SourcesRepositoryImpl(onlineDataSource, offlineDataSource, networkHandler)
    }


}