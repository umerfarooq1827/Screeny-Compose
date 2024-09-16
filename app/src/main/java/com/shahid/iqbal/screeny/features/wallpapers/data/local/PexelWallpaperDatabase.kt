package com.shahid.iqbal.screeny.features.wallpapers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahid.iqbal.screeny.features.wallpapers.models.Photo

@Database(entities = [Photo::class], version = 1)
abstract class PexelWallpaperDatabase : RoomDatabase()