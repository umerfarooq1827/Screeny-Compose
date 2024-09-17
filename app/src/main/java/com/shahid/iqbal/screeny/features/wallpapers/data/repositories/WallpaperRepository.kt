package com.shahid.iqbal.screeny.features.wallpapers.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahid.iqbal.screeny.features.wallpapers.data.paging.PexelWallpaperRemoteMediator
import com.shahid.iqbal.screeny.features.wallpapers.data.utils.Constant
import com.shahid.iqbal.screeny.features.wallpapers.models.Wallpaper
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class WallpaperRepository(private val remoteMediator: PexelWallpaperRemoteMediator) {

    fun getAllWallpapers(): Flow<PagingData<Wallpaper>> {
        return Pager(
            config = PagingConfig(pageSize = Constant.PER_PAGE_ITEMS),
            initialKey = null,
            pagingSourceFactory = remoteMediator,
            remoteMediator = remoteMediator
        ).flow
    }
}