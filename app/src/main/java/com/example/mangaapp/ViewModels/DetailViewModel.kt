package com.example.mangaapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaapp.Database.Models.WebToonModel
import com.example.mangaapp.Repositories.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository):ViewModel() {
    private val _webToon = MutableLiveData<WebToonModel>()
    val webToon:LiveData<WebToonModel>
        get() = _webToon
    fun getWebToon(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
           val toon = repository.getWebToonById(id)
            _webToon.postValue(toon)
        }
    }
}