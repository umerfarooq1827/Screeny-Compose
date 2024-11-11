package com.shahid.iqbal.screeny.ui.screens.settings.language.utils

import androidx.compose.runtime.Immutable

@androidx.annotation.Keep
@Immutable
data class LanguageEntity(
    val countryCode: String,
    val languageName: String,
    val languageCode: String = "en",
    val flag: String
)