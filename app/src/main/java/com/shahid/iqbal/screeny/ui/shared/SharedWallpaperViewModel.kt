package com.shahid.iqbal.screeny.ui.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahid.iqbal.screeny.models.Wallpaper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedWallpaperViewModel : ViewModel() {

    private var _wallpaperList = MutableStateFlow<List<Wallpaper>>(emptyList())
    val wallpaperList get() = _wallpaperList.asStateFlow()


    fun updateWallpaperList(wallpapers: List<Wallpaper>) {
        viewModelScope.launch {
            _wallpaperList.emit(wallpapers)
        }
    }

}