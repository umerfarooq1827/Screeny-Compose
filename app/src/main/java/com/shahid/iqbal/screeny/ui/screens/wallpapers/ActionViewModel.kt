package com.shahid.iqbal.screeny.ui.screens.wallpapers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahid.iqbal.screeny.data.repositories.FavouriteRepo
import com.shahid.iqbal.screeny.models.Wallpaper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ActionViewModel(private val favouriteRepo: FavouriteRepo) : ViewModel() {


    val getAllFavourites
        get() =
            favouriteRepo.getAllFavourites.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addOrRemove(wallpaper: Wallpaper) {
        viewModelScope.launch {
            favouriteRepo.addOrRemove(wallpaper)
        }
    }

    suspend fun getFavouriteById(id: Int) = favouriteRepo.isFavourite(id)
}