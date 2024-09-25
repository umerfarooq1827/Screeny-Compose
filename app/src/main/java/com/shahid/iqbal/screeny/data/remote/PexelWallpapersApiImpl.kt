package com.shahid.iqbal.screeny.data.remote

import androidx.room.Query
import com.shahid.iqbal.screeny.data.utils.Constant.PER_PAGE_ITEMS
import com.shahid.iqbal.screeny.data.utils.HttpRoutes
import com.shahid.iqbal.screeny.models.WallpaperResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class PexelWallpapersApiImpl(private val httpClient: HttpClient) : PexelWallpapersApi {

    override suspend fun getWallpapers(page: Int): WallpaperResponse =
        httpClient.get(HttpRoutes.WALLPAPERS) {
            parameter("page", page)
            parameter("per_page", PER_PAGE_ITEMS)
        }.body()

    override suspend fun searchWallpaper(page: Int, query: String): WallpaperResponse =
        httpClient.get(HttpRoutes.SEARCH_WALLPAPERS) {
            parameter("query", query)
            parameter("page", page)
            parameter("per_page", PER_PAGE_ITEMS)
        }.body()


}