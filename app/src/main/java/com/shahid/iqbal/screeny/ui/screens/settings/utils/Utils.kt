package com.shahid.iqbal.screeny.ui.screens.settings.utils

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


fun findLanguageByCode(code: String): LanguageEntity {
    return LANGUAGES_LIST.find { it.languageCode == code } ?: LANGUAGES_LIST[0]

}