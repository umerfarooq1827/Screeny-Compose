package com.shahid.iqbal.screeny.feature.wallpapers.di

import androidx.room.Room
import com.shahid.iqbal.screeny.feature.wallpapers.data.local.PexelWallpaperDatabase
import com.shahid.iqbal.screeny.feature.wallpapers.data.utils.Constant
import org.koin.dsl.module


val wallpaperDatabaseModule = module {

    single { Room.databaseBuilder(get(), PexelWallpaperDatabase::class.java, Constant.PEXEL_WALLPAPER_DATABASE).fallbackToDestructiveMigration().build() }
    single { get<PexelWallpaperDatabase>().pexelWallpaperDao() }
    single { get<PexelWallpaperDatabase>().pexelWallpaperRemoteKeysDao() }
}