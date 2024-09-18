package com.shahid.iqbal.screeny.core.routs

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {

    @Serializable
    data object Splash : Screens()

    @Serializable
    data object Home : Screens()

    @Serializable
    data object Category : Screens()

    @Serializable
    data object Favourite : Screens()

    @Serializable
    data object Setting : Screens()

}