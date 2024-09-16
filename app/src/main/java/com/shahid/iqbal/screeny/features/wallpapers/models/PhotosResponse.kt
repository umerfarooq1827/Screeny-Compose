package com.shahid.iqbal.screeny.features.wallpapers.models


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PhotosResponse(
    @SerialName("photos")
    val photos: List<Photo>
)