package com.shahid.iqbal.screeny.models

import com.shahid.iqbal.screeny.models.Wallpaper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WallpaperResponse(
    @SerialName("photos")
    val wallpapers: List<Wallpaper>,
)