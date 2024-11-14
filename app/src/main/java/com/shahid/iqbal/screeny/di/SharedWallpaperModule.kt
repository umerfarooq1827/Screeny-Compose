package com.shahid.iqbal.screeny.di

import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.data.repositories.FavouriteRepo
import com.shahid.iqbal.screeny.data.repositories.RecentSearchRepository
import com.shahid.iqbal.screeny.data.repositories.SearchWallpapersRepository
import com.shahid.iqbal.screeny.data.repositories.UserPreferenceRepo
import com.shahid.iqbal.screeny.data.repositories.WallpaperRepository
import com.shahid.iqbal.screeny.models.UserPreference
import org.koin.dsl.module

val sharedWallpaperModule = module {

    single<WallpaperRepository> { WallpaperRepository(get(), get()) }
    single<SearchWallpapersRepository> { SearchWallpapersRepository(get()) }
    single<RecentSearchRepository> { RecentSearchRepository(get()) }
    single<FavouriteRepo> { FavouriteRepo(get()) }
    single<UserPreferenceRepo>{UserPreferenceRepo(get())}

    single<ImageLoader> { ImageLoader.Builder(get()).crossfade(true).build() }
}