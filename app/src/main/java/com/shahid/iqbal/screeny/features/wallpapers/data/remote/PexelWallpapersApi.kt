package com.shahid.iqbal.screeny.features.wallpapers.data.remote

import com.shahid.iqbal.screeny.features.wallpapers.models.PhotosResponse

interface PexelWallpapersApi {

    suspend fun getWallpapers(page:Int): PhotosResponse

}