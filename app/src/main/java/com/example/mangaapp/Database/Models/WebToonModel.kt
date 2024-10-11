package com.example.mangaapp.Database.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "webtoon")
data class WebToonModel(
    @PrimaryKey
    val id:Int,
    var title:String,
    var imageUrl:String,
    var creator:String,
    var rating:Float,
    var description:String,
    var isFavorite:Boolean = false,
    var numberOfratings:Int = 0,
    var avergaOfRating:Float = 0f
)