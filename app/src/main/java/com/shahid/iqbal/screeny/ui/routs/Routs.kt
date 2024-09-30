package com.shahid.iqbal.screeny.ui.routs

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Keep
@Serializable
sealed interface Routs {

    @Serializable
    data object Splash : Routs

    @Serializable
    data object Home : Routs

    @Serializable
    data object Categories : Routs

    @Serializable
    data object Favourite : Routs

    @Serializable
    data object Setting : Routs

    @Serializable
    @Stable
    data class CategoryDetail(val query: String) : Routs

    @Serializable
    data object SearchedWallpaper : Routs

    @Serializable
    data object WallpaperDetail : Routs

}