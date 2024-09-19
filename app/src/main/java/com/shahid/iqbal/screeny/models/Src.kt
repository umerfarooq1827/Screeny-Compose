package com.shahid.iqbal.screeny.models


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Src(
    @SerialName("medium")
    val medium: String,
    @SerialName("portrait")
    val portrait: String,
)