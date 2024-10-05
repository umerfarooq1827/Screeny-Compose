package com.shahid.iqbal.screeny.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.shahid.iqbal.screeny.models.Wallpaper


@Dao
interface PexelWallpaperDao {

    @Query("SELECT * FROM pexel_wallpaper_table order by page")
    fun getAllWallpapers(): PagingSource<Int, Wallpaper>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWallpapers(wallpapers: List<Wallpaper>)

    @Query("DELETE FROM pexel_wallpaper_table")
    suspend fun deleteAllWallpapers()
}