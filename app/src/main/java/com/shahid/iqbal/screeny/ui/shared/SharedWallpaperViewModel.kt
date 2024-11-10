package com.shahid.iqbal.screeny.ui.shared

import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahid.iqbal.screeny.models.Wallpaper
import com.shahid.iqbal.screeny.ui.utils.BitmapUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SharedWallpaperViewModel : ViewModel() {

    private val _wallpaperList = MutableStateFlow<List<Wallpaper>>(emptyList())
    val wallpaperList get() = _wallpaperList.asStateFlow()


    private val _selectedWallpaperIndex = MutableStateFlow(0)
    val selectedWallpaperIndex get() = _selectedWallpaperIndex.asStateFlow()


    private val _currentlyLoadedWallpaper = MutableStateFlow<Drawable?>(null)
    val currentWallpaper get() = _currentlyLoadedWallpaper.asStateFlow()

    private val _luminanceResults = MutableStateFlow(mutableMapOf<String, Boolean>())
    val luminanceResults: StateFlow<Map<String, Boolean>> get() = _luminanceResults


    private fun updateLuminanceResult(wallpaperUrl: String, isLight: Boolean) {
        viewModelScope.launch {
            _luminanceResults.update {
                it[wallpaperUrl] = isLight
                it
            }
        }
    }

    fun performLuminanceWork(url: String, drawable: Drawable) {
        viewModelScope.launch(Dispatchers.Default) {
            if (url !in luminanceResults.value) {
                val bitmap = drawable.toBitmap()
                BitmapUtil.createBitmapCopy(bitmap)?.let { copyBitmap ->
                    val isLight = BitmapUtil.isImageLight(copyBitmap)
                    updateLuminanceResult(wallpaperUrl = url, isLight = isLight)
                }
            }
        }
    }

    fun updateWallpaper(drawable: Drawable) {
        viewModelScope.launch {
            _currentlyLoadedWallpaper.update { drawable }
        }
    }

    fun updateWallpaperList(wallpapers: List<Wallpaper>) {
        viewModelScope.launch {
            _wallpaperList.update { wallpapers }
        }
    }

    fun updateSelectedWallpaper(wallpaper: Wallpaper?) {
        viewModelScope.launch {
            _selectedWallpaperIndex.update { wallpaperList.value.indexOf(wallpaper) }
        }
    }


}