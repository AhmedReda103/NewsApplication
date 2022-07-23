package com.example.newsapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapplication.model.SourcesItem
import com.example.newsapplication.ui.categories.Category

@Dao
interface SourcesDao {

    @Query("select * from SourcesItem")
    suspend fun getSources(): List<SourcesItem?>

    @Query("select * from SourcesItem where category =:category")
    suspend fun getSourcesByCategoryId(category: String): List<SourcesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSources(sources: List<SourcesItem?>?)


}