package com.shahid.iqbal.screeny.ui.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shahid.iqbal.screeny.ui.theme.ScreenyTheme

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
