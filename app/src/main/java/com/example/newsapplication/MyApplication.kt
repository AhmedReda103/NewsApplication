package com.example.newsapplication

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.newsapplication.api.Constants
import com.example.newsapplication.database.MyDatabase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDatabase.init(this)

    }


}

