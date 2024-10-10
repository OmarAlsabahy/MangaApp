package com.example.mangaapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangaapp.Database.Models.WebToonModel
import com.example.mangaapp.Repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {

   private val _webToonList = MutableLiveData<List<WebToonModel>>()
    val webToonModelList : LiveData<List<WebToonModel>>
        get() = _webToonList

   fun initializeData(){
        val data = repository.initializeData()
        _webToonList.value = data
    }
}