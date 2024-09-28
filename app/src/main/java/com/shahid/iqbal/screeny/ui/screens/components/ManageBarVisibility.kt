package com.shahid.iqbal.screeny.ui.screens.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.shahid.iqbal.screeny.ui.routs.Routs
import com.shahid.iqbal.screeny.ui.routs.Routs.CategoryDetail
import com.shahid.iqbal.screeny.ui.routs.Routs.SearchedWallpaper
import com.shahid.iqbal.screeny.ui.routs.Routs.Splash
import com.shahid.iqbal.screeny.ui.routs.Routs.WallpaperDetail
import com.shahid.iqbal.screeny.utils.Extensions.debug

@Composable
fun ManageBarVisibility(currentEntry: NavBackStackEntry?, showTopBar: (Boolean) -> Unit, showBottomBar: (Boolean) -> Unit) {
    currentEntry?.let { entry ->
        when (entry.destination.route?.substringBefore("/")) {

            in arrayOf(
                Splash::class.qualifiedName,
                CategoryDetail::class.qualifiedName,
                SearchedWallpaper::class.qualifiedName,
                WallpaperDetail::class.qualifiedName

            ) -> {
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

