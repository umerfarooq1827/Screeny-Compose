package com.shahid.iqbal.screeny.features.wallpapers.models


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PhotosResponse(
    @SerialName("next_page")
    val nextPage: String, // https://api.pexels.com/v1/curated/?page=3&per_page=40
    @SerialName("page")
    val page: Int, // 2
    @SerialName("per_page")
    val perPage: Int, // 40
    @SerialName("photos")
    val photos: List<Photo>,
    @SerialName("prev_page")
    val prevPage: String, // https://api.pexels.com/v1/curated/?page=1&per_page=40
    @SerialName("total_results")
    val totalResults: Int // 8000
)