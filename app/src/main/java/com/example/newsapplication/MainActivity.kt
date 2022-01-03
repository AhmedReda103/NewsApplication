package com.example.newsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.api.ApiManager
import com.example.newsapplication.api.Constants
import com.example.newsapplication.model.NewsResponse
import com.example.newsapplication.model.SourcesItem
import com.example.newsapplication.model.SourcesResponse
import com.example.newsapplication.ui.news.NewsAdapter
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getNewsSources()
    }

    val adapter = NewsAdapter(null)
    private fun initViews() {
        tabLayout = findViewById(R.id.tab_layout)
        progressBar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
    }

    private fun getNewsSources() {
        ApiManager.getApis().getSources(Constants.apiKey, "")
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressBar.isVisible = false;
                    addSoursesToTabLayout(response.body()?.sources)
                    //Log.e("data", response.body().toString())
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    Log.e("error", t.localizedMessage)
                }
            })
    }

    fun addSoursesToTabLayout(sources: List<SourcesItem?>?) {

        sources?.forEach {
            val tab = tabLayout.newTab()
            tab.setText(it?.name)
            tab.tag = it
            tabLayout.addTab(tab)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //     val source = sources?.get(tab?.position?:0)
                val source = tab?.tag as SourcesItem
                getNewsBySource(source)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                getNewsBySource(source)
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

    }

    fun getNewsBySource(source: SourcesItem) {
        progressBar.isVisible = true
        ApiManager.getApis().getNews(Constants.apiKey, source.id ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressBar.isVisible = false
                    response.body()?.articles
                    adapter.changeData(response.body()?.articles)
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    progressBar.isVisible = false

                }

            });

    }


}
