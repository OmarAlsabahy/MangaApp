package com.example.mangaapp.Database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mangaapp.Database.Models.WebToonModel

@Dao
interface WebToonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(webToon:WebToonModel)
    @Query("select*from webtoon")
    suspend fun getAllFavourites():List<WebToonModel>
    @Delete
    suspend fun deleteFromFavourites(webToon: WebToonModel)
}