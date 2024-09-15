package com.shahid.iqbal.screeny.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shahid.iqbal.screeny.ui.theme.ScreenyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { MainContent() }
    }
}

@Composable
fun MainContent() {
    ScreenyTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ScreenyApp(innerPadding)
        }
    }
}