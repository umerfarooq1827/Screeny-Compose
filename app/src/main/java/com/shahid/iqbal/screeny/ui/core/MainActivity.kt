package com.shahid.iqbal.screeny.ui.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shahid.iqbal.screeny.ui.screens.settings.SettingViewModel
import com.shahid.iqbal.screeny.ui.screens.settings.language.utils.currentAppMode
import com.shahid.iqbal.screeny.ui.theme.ScreenyTheme

class MainActivity : ComponentActivity() {

    private val settingViewModel by viewModels<SettingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val userPreference by settingViewModel.userPreference.collectAsStateWithLifecycle()
            val isDarkMode = currentAppMode(userPreference.appMode)

            ScreenyTheme(
                dynamicColor = userPreference.shouldShowDynamicColor,
                darkTheme = isDarkMode
            ) {
                ScreenyApp()

            }
        }
    }
}
