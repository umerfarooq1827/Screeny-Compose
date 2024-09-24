package com.shahid.iqbal.screeny.ui.screens.components

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> {
        error("NavController not provided")
    }
