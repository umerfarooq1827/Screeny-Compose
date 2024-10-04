package com.shahid.iqbal.screeny.models


import androidx.annotation.Keep
import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Keep
@Serializable
data class Src(
    @SerialName("medium")
    val medium: String,
    @SerialName("portrait")
    val portrait: String,
    @SerialName("small")
    val small:String
)