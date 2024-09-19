package com.shahid.iqbal.screeny.ui.routs

import kotlinx.serialization.Serializable

@Serializable
sealed class Routs {

    @Serializable
    data object Splash : Routs()

    @Serializable
    data object Home : Routs()

    @Serializable
    data object Category : Routs()

    @Serializable
    data object Favourite : Routs()

    @Serializable
    data object Setting : Routs()

}