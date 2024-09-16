package com.shahid.iqbal.screeny.features.wallpapers.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shahid.iqbal.screeny.features.wallpapers.data.utils.Constant.PEXEL_IMAGE_TABLE

@Keep
@Serializable
@Entity(tableName = PEXEL_IMAGE_TABLE)
data class Photo(
    @SerialName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int, // 28271326
    @SerialName("photographer")
    val photographerName: String, // Mathias Reding
    @SerialName("photographer_url")
    val photographerUrl: String, // https://www.pexels.com/@matreding
    @SerialName("src")
    @Embedded val imageSource: Src,
)