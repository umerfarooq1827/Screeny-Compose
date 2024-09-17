package com.shahid.iqbal.screeny.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.collectAsLazyPagingItems
import com.shahid.iqbal.screeny.core.utils.Extensions.debug
import com.shahid.iqbal.screeny.features.wallpapers.data.repositories.WallpaperRepository
import com.shahid.iqbal.screeny.ui.theme.ScreenyTheme
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    val repo: WallpaperRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MainContent()
        }
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