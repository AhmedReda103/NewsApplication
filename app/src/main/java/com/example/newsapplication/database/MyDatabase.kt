package com.example.newsapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapplication.model.SourcesItem

@Database(entities = [SourcesItem::class], version = 1, exportSchema = false)

abstract class MyDatabase : RoomDatabase() {
    abstract fun SourceDao(): SourcesDao

    companion object {
        var database: MyDatabase? = null
        const val DATABASE_NAME = "newsdatabase"

        fun init(context: Context) {
            if (database == null) {
                database = Room.databaseBuilder(context, MyDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

        fun getInstance(): MyDatabase {
            return database!!
        }
    }
}