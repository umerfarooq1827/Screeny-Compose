package com.shahid.iqbal.screeny.feature.wallpapers.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.shahid.iqbal.screeny.feature.wallpapers.models.Wallpaper


@Dao
interface PexelWallpaperDao {

    @Query("SELECT * FROM pexel_wallpaper_table")
    fun getAllWallpapers(): PagingSource<Int, Wallpaper>

    @Upsert
    suspend fun addWallpapers(wallpapers: List<Wallpaper>)

    @Query("DELETE FROM pexel_wallpaper_table")
    suspend fun deleteAllWallpapers()
}