package com.shahid.iqbal.screeny.core.routs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

val bottomNavigationItems = listOf(
    TopLevelRoute("Home", Screens.Home, Icons.Filled.Home),
    TopLevelRoute("Category", Screens.Category, Icons.Filled.DateRange),
    TopLevelRoute("Favourite", Screens.Favourite, Icons.Filled.Favorite),
    TopLevelRoute("Setting", Screens.Setting, Icons.Filled.Settings),
)

