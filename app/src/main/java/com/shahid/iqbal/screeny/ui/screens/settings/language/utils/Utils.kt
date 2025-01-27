package com.shahid.iqbal.screeny.ui.screens.settings.language.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable


@ReadOnlyComposable
@Composable
fun currentAppMode(appMode: AppMode): Boolean = when (appMode) {
    AppMode.DEFAULT -> isSystemInDarkTheme()
    AppMode.LIGHT -> false
    AppMode.DARK -> true
}
