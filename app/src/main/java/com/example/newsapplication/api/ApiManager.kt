package com.example.newsapplication.api

import android.util.Log
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import okhttp3.OkHttpClient
import okhttp3.internal.http2.Http2Connection

import okhttp3.logging.HttpLoggingInterceptor
import java.util.logging.Level


class ApiManager {

    companion object {
        private var retrofit: Retrofit? = null
        private fun getInstance(): Retrofit {
            if (retrofit == null) {
                val logging = HttpLoggingInterceptor(

                    object : HttpLoggingInterceptor.Logger {
                        override fun log(message: String) {
                            Log.e("Api", message)
                        }
                    }
                )
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

                retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        fun getApis(): WebServices {
            return getInstance().create(WebServices::class.java)
        }


    }
}