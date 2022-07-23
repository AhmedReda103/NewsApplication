package com.example.newsapplication.ui.news

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


@InstallIn(FragmentComponent::class)
@Module
object AdaptersModule {

    @Provides
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter()

    }

}