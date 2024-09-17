package com.shahid.iqbal.screeny.features.wallpapers.di

import com.shahid.iqbal.screeny.features.wallpapers.data.repositories.WallpaperRepository
import org.koin.dsl.module

val sharedWallpaperModule = module {

    single { WallpaperRepository(get(), get()) }
}