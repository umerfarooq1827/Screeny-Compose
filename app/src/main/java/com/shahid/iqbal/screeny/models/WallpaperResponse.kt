package com.shahid.iqbal.screeny.models

import androidx.annotation.Keep
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class WallpaperResponse(
    @SerialName("next_page")
    val nextPage: String? = null,
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("photos") val wallpapers: List<Wallpaper>,
    @SerialName("prev_page") val prevPage: String? = null,
    @SerialName("total_results") val totalResults: Int
)

