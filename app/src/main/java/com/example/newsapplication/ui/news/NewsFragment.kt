package com.example.newsapplication.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.api.ApiManager
import com.example.newsapplication.api.Constants
import com.example.newsapplication.databinding.FragmentNewsBinding
import com.example.newsapplication.ui.categories.Category
import com.example.newsapplication.model.NewsResponse
import com.example.newsapplication.model.SourcesItem
import com.example.newsapplication.model.SourcesResponse
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {


    lateinit var viewDataBinding: FragmentNewsBinding

    companion object {
        fun getInstance(category: Category): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }

    lateinit var category: Category

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return inflater.inflate(R.layout.fragment_news, container, false)
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return viewDataBinding.root
    }

    //    lateinit var tabLayout: TabLayout
//    lateinit var progressBar: ProgressBar
//    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        subscribeToLiveData()
        viewModel.getNewsSources(category)
    }

    fun subscribeToLiveData() {
        viewModel.progressBarVisible.observe(viewLifecycleOwner, Observer { isVisible ->
//             if(isVisible)
//                 progressBar.visibility=View.VISIBLE
//             else
//                 progressBar.visibility=View.GONE
            //progressBar.isVisible = isVisible
            viewDataBinding.progressBar.isVisible = isVisible
        })

//        viewModel.sourcesLiveData.observe(viewLifecycleOwner, object : Observer<List<SourcesItem?>?> {
//            override fun onChanged(t: List<SourcesItem?>?) {
//                addSoursesToTabLayout(it)
//            }
//
//       })

        viewModel.sourcesLiveData.observe(viewLifecycleOwner, Observer {
            addSoursesToTabLayout(it)
        })

        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            adapter.changeData(it)
        })

    }

    val adapter = NewsAdapter(null)
    fun initViews() {
//        tabLayout = requireView().findViewById(R.id.tab_layout)
//        progressBar = requireView().findViewById(R.id.progress_bar)
//        recyclerView = requireView().findViewById(R.id.recycler_view)
        viewDataBinding.recyclerView.adapter = adapter
    }

    fun addSoursesToTabLayout(sources: List<SourcesItem?>?) {

        sources?.forEach {
            val tab = viewDataBinding.tabLayout.newTab()
            tab.setText(it?.name)
            tab.tag = it
            viewDataBinding.tabLayout.addTab(tab)
        }

        viewDataBinding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //     val source = sources?.get(tab?.position?:0)
                val source = tab?.tag as SourcesItem
                viewModel.getNewsBySource(source)

                //getNewsBySource(source)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                viewModel.getNewsBySource(source)
                //getNewsBySource(source)
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

    }


}