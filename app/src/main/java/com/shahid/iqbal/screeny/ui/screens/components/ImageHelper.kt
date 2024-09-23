package com.shahid.iqbal.screeny.ui.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.request.ImageRequest

@Composable
fun imageRequestBuilder(image: Any) =
    ImageRequest.Builder(LocalContext.current)
        .data(image)
        .lifecycle(LocalLifecycleOwner.current)
        .crossfade(true)
