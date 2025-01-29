package com.shahid.iqbal.screeny.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahid.iqbal.screeny.data.repositories.UserPreferenceRepo
import com.shahid.iqbal.screeny.models.UserPreference
import com.shahid.iqbal.screeny.ui.screens.settings.utils.AppMode
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingViewModel(private val preferenceRepo: UserPreferenceRepo) : ViewModel() {

    val userPreference = preferenceRepo.uerPreference.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserPreference()
    )


    fun updateDynamicColor(isDynamicColor: Boolean) {
        viewModelScope.launch {
            preferenceRepo.updateDynamicColor(isDynamicColor)
        }
    }

    fun updateAppMode(appMode: AppMode) {
        viewModelScope.launch {
            preferenceRepo.updateAppMode(appMode)
        }
    }
}