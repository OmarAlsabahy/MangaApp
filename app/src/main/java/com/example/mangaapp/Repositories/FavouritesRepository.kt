package com.example.mangaapp.Repositories

import com.example.mangaapp.Database.Dao.WebToonDao
import com.example.mangaapp.Database.Models.WebToonModel
import javax.inject.Inject

class FavouritesRepository @Inject constructor(private val webToonDao: WebToonDao) {
    suspend fun getFavourites():List<WebToonModel>{
        return webToonDao.getAllFavourites()
    }
    suspend fun removeFromFavourites(webToonModel: WebToonModel){
        webToonDao.removeFromFavourite(webToonModel)
    }
}