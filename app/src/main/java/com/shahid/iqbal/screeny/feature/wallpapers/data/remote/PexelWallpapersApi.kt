package com.shahid.iqbal.screeny.feature.wallpapers.data.remote

import com.shahid.iqbal.screeny.feature.wallpapers.models.WallpaperResponse

interface PexelWallpapersApi {

    suspend fun getWallpapers(page: Int): WallpaperResponse

}