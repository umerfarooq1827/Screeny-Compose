package com.shahid.iqbal.screeny.features.wallpapers.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahid.iqbal.screeny.features.wallpapers.data.local.PexelWallpaperDatabase
import com.shahid.iqbal.screeny.features.wallpapers.data.paging.PexelWallpaperRemoteMediator
import com.shahid.iqbal.screeny.features.wallpapers.data.remote.PexelWallpapersApi
import com.shahid.iqbal.screeny.features.wallpapers.data.remote.PexelWallpapersApiImpl
import com.shahid.iqbal.screeny.features.wallpapers.data.utils.Constant
import com.shahid.iqbal.screeny.features.wallpapers.models.Wallpaper
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class WallpaperRepository(private val api: PexelWallpapersApi, private val database: PexelWallpaperDatabase) {

    fun getAllWallpapers(): Flow<PagingData<Wallpaper>> {

        val pageConfig = PagingConfig(pageSize = Constant.PER_PAGE_ITEMS)
        val pagingSourceFactory = { database.pexelWallpaperDao().getAllWallpapers() }
        val remoteMediator = PexelWallpaperRemoteMediator(database, api)

        return Pager(
            config = pageConfig,
            initialKey = null,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = remoteMediator
        ).flow
    }
}