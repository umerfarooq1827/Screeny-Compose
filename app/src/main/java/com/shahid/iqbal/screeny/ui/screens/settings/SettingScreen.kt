package com.shahid.iqbal.screeny.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingScreen(modifier: Modifier = Modifier, settingViewModel: SettingViewModel = koinViewModel()) {


    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

    }
}
