package com.shahid.iqbal.screeny.features.wallpapers.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.shahid.iqbal.screeny.features.wallpapers.models.WallpaperRemoteKeys

@Dao
interface PexelWallpaperRemoteKeysDao {

    @Query("SELECT * FROM pexel_wallpaper_remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): WallpaperRemoteKeys?

    @Upsert
    suspend fun addAllRemoteKeys(remoteKeys: List<WallpaperRemoteKeys>)

    @Query("DELETE FROM pexel_wallpaper_remote_keys_table")
    suspend fun deleteAllRemoteKeys()


}