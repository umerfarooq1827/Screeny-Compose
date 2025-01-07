package com.shahid.iqbal.screeny.models

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shahid.iqbal.screeny.data.utils.Constant.PEXEL_WALLPAPER_REMOTE_KEYS_TABLE


@Entity(PEXEL_WALLPAPER_REMOTE_KEYS_TABLE)
@Keep
data class WallpaperRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val wallpaperId: Long,
    val prevPage: Int?,
    val nextPage: Int?,
    val page: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)