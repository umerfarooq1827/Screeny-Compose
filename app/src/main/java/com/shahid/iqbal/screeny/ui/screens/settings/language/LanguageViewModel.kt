package com.shahid.iqbal.screeny.ui.screens.settings.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahid.iqbal.screeny.data.repositories.UserPreferenceRepo
import com.shahid.iqbal.screeny.ui.screens.settings.language.utils.LANGUAGES_LIST
import com.shahid.iqbal.screeny.ui.screens.settings.language.utils.LanguageEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LanguageViewModel(private val userPreferenceRepo: UserPreferenceRepo) : ViewModel() {

    private val defaultLanguage = LANGUAGES_LIST.first { it.languageCode == "en" }


    val currentLanguage = userPreferenceRepo.uerPreference
        .map { preference ->
            LANGUAGES_LIST.getOrElse(LANGUAGES_LIST.indexOfFirst { it.languageCode == preference.languageCode }) { defaultLanguage }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = defaultLanguage
        )


    fun updateCurrentLanguage(languageEntity: LanguageEntity) {
        viewModelScope.launch {
            userPreferenceRepo.updateAppLanguage(languageEntity)
        }
    }
}