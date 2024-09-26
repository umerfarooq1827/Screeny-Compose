package com.shahid.iqbal.screeny.di

import androidx.room.Room
import com.shahid.iqbal.screeny.data.local.database.PexelWallpaperDatabase
import com.shahid.iqbal.screeny.data.utils.Constant
import org.koin.dsl.module


val wallpaperDatabaseModule = module {

    single { Room.databaseBuilder(get(), PexelWallpaperDatabase::class.java, Constant.PEXEL_WALLPAPER_DATABASE)
        .fallbackToDestructiveMigration()
        .fallbackToDestructiveMigrationOnDowngrade()
        .build() }
    single { get<PexelWallpaperDatabase>().pexelWallpaperDao() }
    single { get<PexelWallpaperDatabase>().pexelWallpaperRemoteKeysDao() }
    single { get<PexelWallpaperDatabase>().recentSearchDao() }
}