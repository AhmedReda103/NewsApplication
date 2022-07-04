package com.example.newsapplication.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R

class CategoriesFragment : Fragment() {


    val categoriesList = listOf(
        Category("sports", R.string.sports, R.drawable.sports, R.color.red),
        Category("general", R.string.entertainment, R.drawable.environment, R.color.blue),
        Category("health", R.string.health, R.drawable.health, R.color.pink),
        Category("science", R.string.science, R.drawable.science, R.color.yellow),
        Category("technology", R.string.technology, R.drawable.politics, R.color.blue_dark),
        Category("business", R.string.business, R.drawable.bussines, R.color.brown)

    )
    val adapter = CategoriesAdapter(categoriesList)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    lateinit var recyclerView: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        adapter.onItemClickListener = object : CategoriesAdapter.OnItemClickListner {
            override fun onItemClick(pos: Int, category: Category) {

                onCategoryClickListner?.onCategoryClick(category)

            }
        }

    }

    var onCategoryClickListner: OnCategoryClickListener? = null

    interface OnCategoryClickListener {
        fun onCategoryClick(category: Category) {

        }

    }

}