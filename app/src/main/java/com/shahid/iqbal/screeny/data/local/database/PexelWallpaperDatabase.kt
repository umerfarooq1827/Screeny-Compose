package com.shahid.iqbal.screeny.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahid.iqbal.screeny.data.local.dao.PexelWallpaperDao
import com.shahid.iqbal.screeny.data.local.dao.PexelWallpaperRemoteKeysDao
import com.shahid.iqbal.screeny.models.WallpaperRemoteKeys
import com.shahid.iqbal.screeny.models.Wallpaper

@Database(entities = [Wallpaper::class, WallpaperRemoteKeys::class], version = 1, exportSchema = false)
abstract class PexelWallpaperDatabase : RoomDatabase() {

    abstract fun pexelWallpaperDao(): PexelWallpaperDao

    abstract fun pexelWallpaperRemoteKeysDao(): PexelWallpaperRemoteKeysDao
}