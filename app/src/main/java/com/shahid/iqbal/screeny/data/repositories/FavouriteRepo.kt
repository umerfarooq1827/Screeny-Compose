package com.shahid.iqbal.screeny.data.repositories

import com.shahid.iqbal.screeny.data.local.dao.FavouriteWallpaperDao
import com.shahid.iqbal.screeny.models.FavouriteWallpaper
import com.shahid.iqbal.screeny.models.CommonWallpaperEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouriteRepo(private val dao: FavouriteWallpaperDao) {

    private val ioDispatcher = Dispatchers.IO

    val getAllFavourites get() = dao.getAllFavourites()

    suspend fun addOrRemove(wallpaper: CommonWallpaperEntity) {
        withContext(ioDispatcher) {
            val savedWallpaper = dao.getFavouriteById(wallpaper.id)

            if (savedWallpaper != null) dao.removeFromFavourite(savedWallpaper)
            else {
                val favouriteWallpaper = FavouriteWallpaper(wallpaper.id, wallpaper.url)
                dao.addToFavourite(favouriteWallpaper)
            }
        }
    }


    suspend fun removeWallpaper(url: String) {
        withContext(ioDispatcher) {
            dao.deleteViaUrl(url)
        }
    }


}