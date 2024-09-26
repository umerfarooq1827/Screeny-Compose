package com.shahid.iqbal.screeny.ui.screens.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.shahid.iqbal.screeny.ui.routs.Routs
import com.shahid.iqbal.screeny.ui.routs.Routs.Splash

@Composable
fun ManageBarVisibility(currentEntry: NavBackStackEntry?, showTopBar: (Boolean) -> Unit, showBottomBar: (Boolean) -> Unit) {
    currentEntry?.let { entry ->
        when (entry.destination.route?.substringBefore("/")) {

            in arrayOf(
                Splash::class.qualifiedName,
                Routs.CategoryDetailScreen::class.qualifiedName,
                Routs.SearchedWallpaperScreen::class.qualifiedName
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

