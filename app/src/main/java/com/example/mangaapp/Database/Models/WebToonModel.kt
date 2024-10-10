package com.example.mangaapp.Database.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "webtoon")
data class WebToonModel(
    @PrimaryKey
    val id:Int,
    val title:String,
    val imageUrl:String,
    val creator:String,
    val rating:Float,
    val description:String
)