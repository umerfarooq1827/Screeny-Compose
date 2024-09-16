package com.shahid.iqbal.screeny.features.wallpapers.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Photo(
    @SerialName("alt")
    val alt: String, // An empty road with mountains in the background
    @SerialName("avg_color")
    val avgColor: String, // #97A2A6
    @SerialName("height")
    val height: Int, // 6240
    @SerialName("id")
    val id: Int, // 28271326
    @SerialName("liked")
    val liked: Boolean, // false
    @SerialName("photographer")
    val photographer: String, // Mathias Reding
    @SerialName("photographer_id")
    val photographerId: Int, // 2848684
    @SerialName("photographer_url")
    val photographerUrl: String, // https://www.pexels.com/@matreding
    @SerialName("src")
    val src: Src,
    @SerialName("url")
    val url: String, // https://www.pexels.com/photo/an-empty-road-with-mountains-in-the-background-28271326/
    @SerialName("width")
    val width: Int // 4160
)