package com.shahid.iqbal.screeny.ui.routs

import kotlinx.serialization.Serializable

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
    data class CategoryDetailScreen(val query: String) : Routs

    @Serializable
    data object SearchedWallpaperScreen : Routs

}