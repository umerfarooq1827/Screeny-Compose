package com.shahid.iqbal.screeny.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("favourite_wallpaper")
data class FavouriteWallpaper(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val wallpaper: String,
    val timeStamp: Long = System.currentTimeMillis()
)