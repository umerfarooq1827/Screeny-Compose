package com.shahid.iqbal.screeny.features.wallpapers.models

import androidx.room.Entity
import com.shahid.iqbal.screeny.features.wallpapers.data.utils.Constant.PEXEL_WALLPAPER_REMOTE_KEYS_TABLE


@Entity(PEXEL_WALLPAPER_REMOTE_KEYS_TABLE)
data class WallpaperRemoteKeys(
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)