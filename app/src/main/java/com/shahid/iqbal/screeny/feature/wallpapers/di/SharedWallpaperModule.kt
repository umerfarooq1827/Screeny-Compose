package com.shahid.iqbal.screeny.feature.wallpapers.di

import com.shahid.iqbal.screeny.feature.wallpapers.data.repositories.WallpaperRepository
import org.koin.dsl.module

val sharedWallpaperModule = module {

    single { WallpaperRepository(get(), get()) }
}