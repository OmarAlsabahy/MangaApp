package com.example.mangaapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mangaapp.Database.Dao.WebToonDao
import com.example.mangaapp.Database.Models.WebToonModel

@Database(entities = [WebToonModel::class] , version = 1 , exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWebToonDao(): WebToonDao
}