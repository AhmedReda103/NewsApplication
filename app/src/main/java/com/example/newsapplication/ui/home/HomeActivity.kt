package com.example.newsapplication.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView

import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapplication.R
import com.example.newsapplication.ui.SettingsFragment
import com.example.newsapplication.ui.categories.Category
import com.example.newsapplication.ui.categories.CategoriesFragment
import com.example.newsapplication.ui.news.NewsFragment

class HomeActivity : AppCompatActivity() {


    lateinit var drawerLayout: DrawerLayout
    lateinit var drawerButton: ImageView
    lateinit var settings: View
    lateinit var categories: View

    val categoriesfragment = CategoriesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViews()
        pushFragment(categoriesfragment)

        categoriesfragment.onCategoryClickListner =
            object : CategoriesFragment.OnCategoryClickListener {
                override fun onCategoryClick(category: Category) {

                    pushFragment(NewsFragment.getInstance(category), true)


                }
            }

    }

    private fun initViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerButton = findViewById(R.id.menu_button)
        settings = findViewById(R.id.settings)
        categories = findViewById(R.id.categories)
        drawerButton.setOnClickListener {
            drawerLayout.open()
        }
        settings.setOnClickListener {
            pushFragment(SettingsFragment())
        }
        categories.setOnClickListener {
            pushFragment(categoriesfragment)

        }
    }

    fun pushFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)

        if (addToBackStack)
            fragmentTransaction.addToBackStack("")
        fragmentTransaction.commit()
        drawerLayout.close()

    }
}