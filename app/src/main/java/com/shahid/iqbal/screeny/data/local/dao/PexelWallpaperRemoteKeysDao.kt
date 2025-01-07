package com.shahid.iqbal.screeny.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.shahid.iqbal.screeny.models.WallpaperRemoteKeys

@Dao
interface PexelWallpaperRemoteKeysDao {

    @Query("SELECT * FROM pexel_wallpaper_remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeyByWallpaperId(id: Long): WallpaperRemoteKeys?

    @Query("Select created_at From pexel_wallpaper_remote_keys_table Order By created_at DESC LIMIT 1")
    suspend fun getCreationTime(): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<WallpaperRemoteKeys>)

    @Query("DELETE FROM pexel_wallpaper_remote_keys_table")
    suspend fun deleteAllRemoteKeys()


}