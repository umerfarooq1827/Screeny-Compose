package com.shahid.iqbal.screeny.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shahid.iqbal.screeny.data.repositories.WallpaperRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


class WallpaperViewModel(private val repo: WallpaperRepository) : ViewModel() {

    val getAllWallpapers
        get() = repo.getAllWallpapers()
            .flow
            .cachedIn(viewModelScope)
}