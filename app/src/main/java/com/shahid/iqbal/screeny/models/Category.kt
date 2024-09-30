package com.shahid.iqbal.screeny.models

import androidx.annotation.Keep
import androidx.compose.runtime.Stable


@Stable
@Keep
data class Category(
    val name: String,
    val thumbnail: String
)