package com.shahid.iqbal.screeny.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahid.iqbal.screeny.data.paging.SearchWallpapersPagingSource
import com.shahid.iqbal.screeny.data.remote.PexelWallpapersApi
import com.shahid.iqbal.screeny.data.utils.Constant
import com.shahid.iqbal.screeny.models.Wallpaper
import kotlinx.coroutines.flow.Flow

class SearchWallpapersRepository(private val api: PexelWallpapersApi) {

    fun getSearchWallpapers(query: String): Pager<Int, Wallpaper> {

        val pageConfig = PagingConfig(pageSize = Constant.PER_PAGE_ITEMS)
        return Pager(
            config = pageConfig,
            pagingSourceFactory = { SearchWallpapersPagingSource(api, query) },
        )
    }
}
