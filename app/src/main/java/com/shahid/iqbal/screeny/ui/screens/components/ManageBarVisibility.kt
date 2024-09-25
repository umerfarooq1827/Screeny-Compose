package com.shahid.iqbal.screeny.ui.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shahid.iqbal.screeny.ui.routs.Routs
import com.shahid.iqbal.screeny.ui.routs.Routs.SearchedWallpaper
import com.shahid.iqbal.screeny.ui.routs.Routs.Splash
import com.shahid.iqbal.screeny.ui.screens.search.SearchedWallpaperScreen
import com.shahid.iqbal.screeny.utils.Extensions.debug

@Composable
fun ManageBarVisibility(currentEntry: NavBackStackEntry?, showTopBar: (Boolean) -> Unit, showBottomBar: (Boolean) -> Unit) {
    currentEntry?.let { entry ->
        when (entry.destination.route?.substringAfterLast(".")?.substringBefore("/")) {

            in arrayOf(Splash::class.qualifiedName, SearchedWallpaper::class.simpleName) -> {
                showTopBar(false)
                showBottomBar(false)
            }

            else -> {
                showTopBar(true)
                showBottomBar(true)
            }
        }
    }
}

