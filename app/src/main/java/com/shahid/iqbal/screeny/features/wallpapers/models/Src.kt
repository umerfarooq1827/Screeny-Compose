package com.shahid.iqbal.screeny.features.wallpapers.models


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Src(
    @SerialName("landscape")
    val landscape: String, // https://images.pexels.com/photos/28271326/pexels-photo-28271326.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200
    @SerialName("large")
    val large: String, // https://images.pexels.com/photos/28271326/pexels-photo-28271326.jpeg?auto=compress&cs=tinysrgb&h=650&w=940
    @SerialName("large2x")
    val large2x: String, // https://images.pexels.com/photos/28271326/pexels-photo-28271326.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940
    @SerialName("medium")
    val medium: String, // https://images.pexels.com/photos/28271326/pexels-photo-28271326.jpeg?auto=compress&cs=tinysrgb&h=350
    @SerialName("original")
    val original: String, // https://images.pexels.com/photos/28271326/pexels-photo-28271326.jpeg
    @SerialName("portrait")
    val portrait: String, // https://images.pexels.com/photos/28271326/pexels-photo-28271326.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800
    @SerialName("small")
    val small: String, // https://images.pexels.com/photos/28271326/pexels-photo-28271326.jpeg?auto=compress&cs=tinysrgb&h=130
    @SerialName("tiny")
    val tiny: String // https://images.pexels.com/photos/28271326/pexels-photo-28271326.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280
)