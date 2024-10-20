package com.shahid.iqbal.screeny.ui.screens.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahid.iqbal.screeny.data.repositories.FavouriteRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class FavouriteViewModel(private val favouriteRepo: FavouriteRepo) : ViewModel() {

    val getAllFavourites
        get() =
            favouriteRepo.getAllFavourites

}