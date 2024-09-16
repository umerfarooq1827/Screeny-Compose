package com.shahid.iqbal.screeny.features.wallpapers.di

import androidx.room.Room
import com.shahid.iqbal.screeny.features.wallpapers.data.local.PexelWallpaperDatabase
import com.shahid.iqbal.screeny.features.wallpapers.data.utils.Constant
import org.koin.dsl.module


val wallpaperDatabaseModule = module {

    single {
        Room.databaseBuilder(get(), PexelWallpaperDatabase::class.java, Constant.PEXEL_WALLPAPER_DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }
}