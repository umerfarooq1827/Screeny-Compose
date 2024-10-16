package com.shahid.iqbal.screeny.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shahid.iqbal.screeny.data.repositories.SearchWallpapersRepository

class CategoryViewModel(private val repo: SearchWallpapersRepository) : ViewModel() {

    fun searchWallpapers(query: String) =
        repo.getSearchWallpapers(query)
            .flow
            .cachedIn(viewModelScope)

}