package com.shahid.iqbal.screeny.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shahid.iqbal.screeny.data.repositories.SearchWallpapersRepository

class SearchWallpaperViewModel(private val repo: SearchWallpapersRepository) : ViewModel() {

    fun searchWallpapers(query: String) = repo.getSearchWallpapers(query)
        .cachedIn(viewModelScope)

}