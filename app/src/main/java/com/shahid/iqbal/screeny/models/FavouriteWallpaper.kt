package com.shahid.iqbal.screeny.models

import androidx.annotation.Keep
import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Keep
@Entity("favourite_wallpaper")
data class FavouriteWallpaper(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val wallpaper: String,
    val timeStamp: Long = System.currentTimeMillis()
)