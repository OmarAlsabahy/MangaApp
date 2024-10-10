package com.example.mangaapp.Database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mangaapp.Database.Models.WebToonModel

@Dao
interface WebToonDao {
    @Query("select * from webtoon")
    suspend fun getAllToons():List<WebToonModel>
    @Update
    suspend fun addToFavourite(webToonModel: WebToonModel)
    @Query("select * from webtoon where isFavorite = 1")
    suspend fun getAllFavourites():List<WebToonModel>
    @Insert
    suspend fun addToons(webToons:List<WebToonModel>)
    @Update
    suspend fun updateDao(webToonModel: WebToonModel)
}