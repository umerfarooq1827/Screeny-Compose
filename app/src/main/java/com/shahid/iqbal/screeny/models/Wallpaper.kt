package com.shahid.iqbal.screeny.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shahid.iqbal.screeny.data.utils.Constant.PEXEL_WALLPAPER_TABLE
import com.shahid.iqbal.screeny.models.Src

@Keep
@Serializable
@Entity(tableName = PEXEL_WALLPAPER_TABLE)
data class Wallpaper(
    @SerialName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerialName("photographer")
    val photographerName: String, // Mathias Reding
    @SerialName("photographer_url")
    val photographerUrl: String, // https://www.pexels.com/@matreding
    @SerialName("src")
    @Embedded val wallpaperSource: Src,
)