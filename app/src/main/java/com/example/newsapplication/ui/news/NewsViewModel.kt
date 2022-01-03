package com.example.newsapplication.ui.news

import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapplication.api.ApiManager
import com.example.newsapplication.api.Constants
import com.example.newsapplication.model.ArticlesItem
import com.example.newsapplication.model.NewsResponse
import com.example.newsapplication.model.SourcesItem
import com.example.newsapplication.model.SourcesResponse
import com.example.newsapplication.ui.categories.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class NewsViewModel : ViewModel() {

    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val progressBarVisible = MutableLiveData(false)
    val newsList = MutableLiveData<List<ArticlesItem?>?>()

    fun getNewsSources(category: Category) {
        progressBarVisible.value = true
        ApiManager.getApis().getSources(Constants.apiKey, category.id)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressBarVisible.value = false
                    //progressBar.isVisible = false;

                    sourcesLiveData.value = response.body()?.sources

                    //addSoursesToTabLayout(response.body()?.sources)
                    //Log.e("data", response.body().toString())
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    Log.e("error", t.localizedMessage)
                    progressBarVisible.value = false

                }
            })
    }

    fun getNewsBySource(source: SourcesItem) {
        progressBarVisible.value = true
        ApiManager.getApis().getNews(Constants.apiKey, source.id ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressBarVisible.value = false
                    newsList.value = response.body()?.articles
                    //adapter.changeData(response.body()?.articles)
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    progressBarVisible.value = false


                }

            });

    }


}