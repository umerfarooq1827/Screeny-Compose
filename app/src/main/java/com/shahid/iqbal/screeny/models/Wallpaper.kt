package com.shahid.iqbal.screeny.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import androidx.compose.runtime.Immutable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shahid.iqbal.screeny.data.utils.Constant.PEXEL_WALLPAPER_TABLE
import com.shahid.iqbal.screeny.models.Src

@Keep
@Immutable
@Serializable
@Entity(tableName = PEXEL_WALLPAPER_TABLE)
data class Wallpaper(
    @SerialName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerialName("photographer")
    val photographerName: String,
    @SerialName("photographer_url")
    val photographerUrl: String,
    @SerialName("src")
    @Embedded val wallpaperSource: Src,
    @SerialName("created_at")
    val createdAt: Long = System.currentTimeMillis()


)