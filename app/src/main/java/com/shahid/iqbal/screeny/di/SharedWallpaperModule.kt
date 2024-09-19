package com.shahid.iqbal.screeny.di

import com.shahid.iqbal.screeny.data.repositories.WallpaperRepository
import org.koin.dsl.module

val sharedWallpaperModule = module {

    single { WallpaperRepository(get(), get()) }
}