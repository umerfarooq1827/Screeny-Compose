package com.shahid.iqbal.screeny.ui.screens.wallpapers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahid.iqbal.screeny.data.repositories.FavouriteRepo
import com.shahid.iqbal.screeny.models.Wallpaper
import kotlinx.coroutines.launch

class ActionViewModel(private val favouriteRepo: FavouriteRepo) : ViewModel() {


    fun addOrRemove(wallpaper: Wallpaper) {
        viewModelScope.launch {
            favouriteRepo.addOrRemove(wallpaper)
        }
    }
}