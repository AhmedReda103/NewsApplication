package com.example.newsapplication.ui.news

import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.MyApplication
import com.example.newsapplication.NetworkHandler
import com.example.newsapplication.api.Constants
import com.example.newsapplication.database.MyDatabase
import com.example.newsapplication.model.ArticlesItem
import com.example.newsapplication.model.NewsResponse
import com.example.newsapplication.model.SourcesItem
import com.example.newsapplication.model.SourcesResponse
import com.example.newsapplication.repos.news.NewsOnlineDataSource
import com.example.newsapplication.repos.news.NewsOnlineDataSourceImpl
import com.example.newsapplication.repos.news.NewsRepository
import com.example.newsapplication.repos.news.NewsRepositoryImpl
import com.example.newsapplication.repos.source.*
import com.example.newsapplication.ui.categories.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val newsRepository: NewsRepository,
    val sourceRepository: SourcesRepository
) : ViewModel() {

    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val progressBarVisible = MutableLiveData(false)
    val newsList = MutableLiveData<List<ArticlesItem?>?>()
    val messageLiveData = MutableLiveData<String>()

//    lateinit var newsOnlineDataSource : NewsOnlineDataSource
//    lateinit var sourcesOnlineDataSource: SourcesOnlineDataSource
//    lateinit var sourcesOfflineDataSource: SourcesOfflineDataSource
//    lateinit var networkHandler: NetworkHandler

//    init {
//        newsOnlineDataSource = NewsOnlineDataSourceImpl(ApiManager.getApis())
//        newsRepository = NewsRepositoryImpl(newsOnlineDataSource)
//        sourcesOfflineDataSource = SourcesOfflineDataSourceImpl(MyDatabase.getInstance())
//        sourcesOnlineDataSource = SourcesOnlineDataSourceImpl(ApiManager.getApis())
//        sourceRepository = SourcesRepositoryImpl(
//            sourcesOnlineDataSource ,sourcesOfflineDataSource,networkHandler
//        )
//    }

    fun getNewsSources(category: Category) {

        viewModelScope.launch {
            try {
                progressBarVisible.value = true
                // val sourcesResponse :SourcesResponse = ApiManager.getApis().getSources(Constants.apiKey, category.id)
                val sourcesResponse = sourceRepository.getSources(category.id)
                progressBarVisible.value = false
                sourcesLiveData.value = sourcesResponse

            } catch (e: Exception) {
                progressBarVisible.value = false
                messageLiveData.value = e.localizedMessage
            }
        }


//        ApiManager.getApis().getSources(Constants.apiKey, category.id)
//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    progressBarVisible.value = false
//                    //progressBar.isVisible = false;
//
//                    sourcesLiveData.value = response.body()?.sources
//
//                    //addSoursesToTabLayout(response.body()?.sources)
//                    //Log.e("data", response.body().toString())
//                }
//
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//                    Log.e("error", t.localizedMessage)
//                    progressBarVisible.value = false
//
//                }
//            })
    }

    fun getNewsBySource(source: SourcesItem) {
        progressBarVisible.value = true

        viewModelScope.launch {
            progressBarVisible.value = false
            try {
                //val newsResponse : NewsResponse = ApiManager.getApis().getNews(Constants.apiKey , source.id?:"")
                val newsResponse = newsRepository.getNews(source.id!!)
                newsList.value = newsResponse

            } catch (e: Exception) {
                progressBarVisible.value = false
                messageLiveData.value = e.localizedMessage
            }
        }


//        ApiManager.getApis().getNews(Constants.apiKey, source.id ?: "")
//            .enqueue(object : Callback<NewsResponse> {
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//                    progressBarVisible.value = false
//                    newsList.value = response.body()?.articles
//                    //adapter.changeData(response.body()?.articles)
//                }
//
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                    progressBarVisible.value = false
//
//
//                }
//
//            });

    }


}