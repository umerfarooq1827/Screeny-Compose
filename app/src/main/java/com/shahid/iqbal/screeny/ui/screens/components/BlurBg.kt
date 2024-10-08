package com.shahid.iqbal.screeny.ui.screens.components

import BlurTransformation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BlurBg(wallpaperUrl:String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(wallpaperUrl)
            .crossfade(true).transformations(BlurTransformation(scale = 0.5f, radius = 15)).build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.7f), Color.Transparent
                    )
                )
            )
    )
}