package com.shahid.iqbal.screeny.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WallpaperResponse(

    @SerialName("next_page")
    val nextPage: String? = null, // https://api.pexels.com/v1/curated/?page=3&per_page=40
    @SerialName("page")
    val page: Int, // 2
    @SerialName("per_page")
    val perPage: Int, // 40
    @SerialName("photos") val wallpapers: List<Wallpaper>,
    @SerialName("prev_page") val prevPage: String? = null, // https://api.pexels.com/v1/curated/?page=1&per_page=40
    @SerialName("total_results")
    val totalResults: Int // 8000
)

