package com.shahid.iqbal.screeny.ui.screens.settings

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shahid.iqbal.screeny.ui.screens.settings.language.utils.AppMode
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingScreen(modifier: Modifier = Modifier, settingViewModel: SettingViewModel = koinViewModel()) {

    val userPreference by settingViewModel.userPreference.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {


    }
}
