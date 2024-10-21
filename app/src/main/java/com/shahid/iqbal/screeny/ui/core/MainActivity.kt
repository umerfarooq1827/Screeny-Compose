package com.shahid.iqbal.screeny.ui.core

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.compose.rememberNavController
import com.shahid.iqbal.screeny.ui.theme.ScreenyTheme
import com.shahid.iqbal.screeny.ui.utils.ComponentHelpers.SetStatusBarBarColor

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ScreenyTheme(dynamicColor = true) {
                ScreenyApp()
            }
        }
    }
}
