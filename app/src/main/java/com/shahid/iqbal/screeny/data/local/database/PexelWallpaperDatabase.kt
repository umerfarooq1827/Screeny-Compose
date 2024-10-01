package com.shahid.iqbal.screeny.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahid.iqbal.screeny.data.local.dao.FavouriteWallpaperDao
import com.shahid.iqbal.screeny.data.local.dao.PexelWallpaperDao
import com.shahid.iqbal.screeny.data.local.dao.PexelWallpaperRemoteKeysDao
import com.shahid.iqbal.screeny.data.local.dao.RecentSearchDao
import com.shahid.iqbal.screeny.models.FavouriteWallpaper
import com.shahid.iqbal.screeny.models.RecentSearch
import com.shahid.iqbal.screeny.models.Wallpaper
import com.shahid.iqbal.screeny.models.WallpaperRemoteKeys

@Database(
    entities = [Wallpaper::class,
        WallpaperRemoteKeys::class,
        RecentSearch::class, FavouriteWallpaper::class], version = 1, exportSchema = false
)
abstract class PexelWallpaperDatabase : RoomDatabase() {

    abstract fun pexelWallpaperDao(): PexelWallpaperDao

    abstract fun pexelWallpaperRemoteKeysDao(): PexelWallpaperRemoteKeysDao

    abstract fun recentSearchDao(): RecentSearchDao

    abstract fun favouriteWallpaperDao(): FavouriteWallpaperDao

}