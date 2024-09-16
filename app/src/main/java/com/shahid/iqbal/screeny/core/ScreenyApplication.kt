package com.shahid.iqbal.screeny.core

import android.app.Application
import com.shahid.iqbal.screeny.core.di.networkModule
import com.shahid.iqbal.screeny.features.wallpapers.di.wallpaperDatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ScreenyApplication : Application() {


    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@ScreenyApplication)
            androidLogger(Level.ERROR)
            modules(networkModule, wallpaperDatabaseModule)
        }

    }
}