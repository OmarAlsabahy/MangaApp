package com.example.mangaapp.Repositories

import com.example.mangaapp.Database.Dao.WebToonDao
import com.example.mangaapp.Database.Models.WebToonModel
import javax.inject.Inject

class DetailRepository @Inject constructor(private val webToonDao: WebToonDao) {
    suspend fun getWebToonById(id:Int):WebToonModel{
        val webToon = webToonDao.getWebToonById(id)
        return webToon
    }
}