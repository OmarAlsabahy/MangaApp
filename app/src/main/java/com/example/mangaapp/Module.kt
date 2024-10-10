package com.example.mangaapp

import android.content.Context
import androidx.room.Room
import com.example.mangaapp.Database.AppDatabase
import com.example.mangaapp.Database.Dao.WebToonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context
        ,AppDatabase::class.java ,"MangaDB")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun getWebToonDao(appDatabase: AppDatabase): WebToonDao {
        return appDatabase.getWebToonDao()
    }
}