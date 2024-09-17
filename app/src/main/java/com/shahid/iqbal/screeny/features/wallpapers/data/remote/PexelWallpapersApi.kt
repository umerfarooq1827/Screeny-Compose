package com.shahid.iqbal.screeny.features.wallpapers.data.remote

import com.shahid.iqbal.screeny.features.wallpapers.models.WallpaperResponse

interface PexelWallpapersApi {

    suspend fun getWallpapers(page:Int): WallpaperResponse

}