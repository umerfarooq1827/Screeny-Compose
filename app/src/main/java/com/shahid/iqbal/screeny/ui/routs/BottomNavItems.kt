package com.shahid.iqbal.screeny.ui.routs

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.shahid.iqbal.screeny.R


data class BottomNavRoutes(@StringRes val name: Int, val route: Routs, val icon: ImageVector)

val bottomNavigationItems = listOf(
    BottomNavRoutes(R.string.home, Routs.Home, Icons.Filled.Home),
    BottomNavRoutes(R.string.category, Routs.Category, Icons.Filled.DateRange),
    BottomNavRoutes(R.string.favourite, Routs.Favourite, Icons.Filled.Favorite),
    BottomNavRoutes(R.string.setting, Routs.Setting, Icons.Filled.Settings)
)

