package com.shahid.iqbal.screeny.features.wallpapers.di

import com.shahid.iqbal.screeny.features.wallpapers.data.remote.PexelPhotoApi
import com.shahid.iqbal.screeny.features.wallpapers.data.remote.PexelPhotoApiImpl
import org.koin.dsl.module


val wallpaperApiModule = module {

    single<PexelPhotoApi> { PexelPhotoApiImpl(get()) }
}