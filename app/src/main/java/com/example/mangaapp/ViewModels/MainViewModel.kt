package com.example.mangaapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaapp.Database.Models.WebToonModel
import com.example.mangaapp.Repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {

   private val _webToonList = MutableLiveData<List<WebToonModel>>()
    val webToonModelList : LiveData<List<WebToonModel>>
        get() = _webToonList

   fun initializeData(){
       viewModelScope.launch (Dispatchers.IO){
           val data = repository.getToons()
           _webToonList.postValue(data)
       }
    }

    fun updateRate(webToonModel: WebToonModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRate(webToonModel)
        }

    }
    fun addToFavourite(webToonModel: WebToonModel){
        viewModelScope.launch (Dispatchers.IO){
            repository.addToFavourite(webToonModel)
        }
    }

    fun removeFromFavourite(webToonModel: WebToonModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromFavourite(webToonModel)
        }

    }
}