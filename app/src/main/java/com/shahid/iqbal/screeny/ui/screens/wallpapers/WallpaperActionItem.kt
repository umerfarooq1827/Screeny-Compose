package com.shahid.iqbal.screeny.ui.screens.wallpapers

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.shahid.iqbal.screeny.R

@Immutable
data class WallpaperActionItem(
    @DrawableRes val icon: Int,
    @StringRes val title: Int
) {

    companion object {

        val WALLPAPER_ACTION_ITEMS =
            listOf(
                WallpaperActionItem(R.drawable.set_as_home_screen, R.string.set_as_home_screen),
                WallpaperActionItem(R.drawable.set_as_lock_screen, R.string.set_as_lock_screen),
                WallpaperActionItem(R.drawable.set_as_both, R.string.set_as_both)
            )
    }
}


