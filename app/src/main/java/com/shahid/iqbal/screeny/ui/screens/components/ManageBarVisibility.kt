package com.shahid.iqbal.screeny.ui.screens.components

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.shahid.iqbal.screeny.ui.routs.Routs.CategoryDetail
import com.shahid.iqbal.screeny.ui.routs.Routs.SearchedWallpaper
import com.shahid.iqbal.screeny.ui.routs.Routs.Splash
import com.shahid.iqbal.screeny.ui.routs.Routs.WallpaperDetail
import com.shahid.iqbal.screeny.ui.utils.ComponentHelpers.HideSystemBars
import com.shahid.iqbal.screeny.ui.utils.ComponentHelpers.SetStatusBarBarColor

@Composable
fun ManageBarVisibility(context: Context, currentEntry: () -> NavBackStackEntry?, showTopBar: (Boolean) -> Unit, showBottomBar: (Boolean) -> Unit) {
    currentEntry()?.let { entry ->

        val route = entry.destination.route?.substringBefore("/")
        when (route) {

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

        if (route in arrayOf(Splash::class.qualifiedName)) {
            HideSystemBars(true)
        }

        if (route !in arrayOf(WallpaperDetail::class.qualifiedName)){
            SetStatusBarBarColor(isDarkMode = isSystemInDarkTheme())
        }
    }
}

