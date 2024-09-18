package com.shahid.iqbal.screeny.feature.wallpapers.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WallpaperResponse(
    @SerialName("photos")
    val wallpapers: List<Wallpaper>,
)