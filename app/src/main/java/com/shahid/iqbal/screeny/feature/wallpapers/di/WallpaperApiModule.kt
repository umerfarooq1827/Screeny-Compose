package com.shahid.iqbal.screeny.feature.wallpapers.di

import com.shahid.iqbal.screeny.feature.wallpapers.data.remote.PexelWallpapersApi
import com.shahid.iqbal.screeny.feature.wallpapers.data.remote.PexelWallpapersApiImpl
import org.koin.dsl.module


val wallpaperApiModule = module {

    single<PexelWallpapersApi> { PexelWallpapersApiImpl(get()) }
}