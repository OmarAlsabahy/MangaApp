package com.example.mangaapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaapp.Database.Models.WebToonModel
import com.example.mangaapp.Repositories.FavouritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavouritesViewModel @Inject constructor(private val favouritesRepository: FavouritesRepository) : ViewModel() {
    private val _webToons = MutableLiveData<List<WebToonModel>>()
    val webToons : LiveData<List<WebToonModel>>
        get() = _webToons

    fun getFavourites(){
        viewModelScope.launch (Dispatchers.IO){
            val toons = favouritesRepository.getFavourites()
            _webToons.postValue(toons)
        }
    }
    fun removeFromFavourite(currentWebToonModel: WebToonModel){
        viewModelScope.launch (Dispatchers.IO){
            favouritesRepository.removeFromFavourites(currentWebToonModel)
        }
    }
    fun refreshData(){
        viewModelScope.launch (Dispatchers.IO){
            val toons = favouritesRepository.getFavourites()
            _webToons.postValue(toons)
        }
    }
}