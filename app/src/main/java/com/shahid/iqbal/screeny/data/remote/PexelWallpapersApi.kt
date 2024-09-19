package com.shahid.iqbal.screeny.data.remote

import com.shahid.iqbal.screeny.models.WallpaperResponse

interface PexelWallpapersApi {

    suspend fun getWallpapers(page: Int): WallpaperResponse

}