package com.shahid.iqbal.screeny.ui.screens.favourite

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.shahid.iqbal.screeny.ui.screens.components.shimmerBrush

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FavouriteWallpaperItem(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    wallpaper: String,
    imageLoader: ImageLoader,
    onWallpaperClick: (String) -> Unit = {}
) {

    var showShimmer by remember { mutableStateOf(true) }

    AsyncImage(
        model = wallpaper,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        imageLoader = imageLoader,
        onSuccess = { showShimmer = false },
        modifier = modifier
            .sharedElement(
                rememberSharedContentState(
                    key = "image-$wallpaper"
                ),
                animatedVisibilityScope = animatedVisibilityScope,
            )
            .background(
                shimmerBrush(targetValue = 1300f, showShimmer = showShimmer), shape = RoundedCornerShape(10.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .height(200.dp)
            .fillMaxWidth()
            .clickable { onWallpaperClick(wallpaper) }

    )

}