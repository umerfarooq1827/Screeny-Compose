package com.shahid.iqbal.screeny.di

import com.shahid.iqbal.screeny.utils.WallpaperDownloader
import org.koin.dsl.module


val appModule = module {

    single { WallpaperDownloader(get()) }
}
