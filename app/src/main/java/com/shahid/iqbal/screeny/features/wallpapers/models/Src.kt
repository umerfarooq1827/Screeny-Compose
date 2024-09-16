package com.shahid.iqbal.screeny.features.wallpapers.models


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Src(
    @SerialName("large")
    val large: String, // https://images.pexels.com/photos/28271326/pexels-photo-28271326.jpeg?auto=compress&cs=tinysrgb&h=650&w=940
    @SerialName("medium")
    val medium: String, // https://images.pexels.com/photos/28271326/pexels-photo-28271326.jpeg?auto=compress&cs=tinysrgb&h=350
    @SerialName("portrait")
    val portrait: String, // https://images.pexels.com/photos/28271326/pexels-photo-28271326.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800
)