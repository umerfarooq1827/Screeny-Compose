package com.shahid.iqbal.screeny.ui.screens.favourite

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.ui.screens.components.shimmerBrush
import com.shahid.iqbal.screeny.ui.theme.ActionIconBgColor

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FavouriteWallpaperItem(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    wallpaper: String,
    imageLoader: ImageLoader,
    onWallpaperClick: (String) -> Unit,
    onRemoveFromFavClick: (String) -> Unit
) {

    var showShimmer by remember { mutableStateOf(true) }


    Box(
        modifier = Modifier
            .sharedElement(
                rememberSharedContentState(key = "image-$wallpaper"),
                animatedVisibilityScope = animatedVisibilityScope,
                placeHolderSize = SharedTransitionScope.PlaceHolderSize.animatedSize
            )
            .fillMaxWidth()
            .height(200.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer), shape = RoundedCornerShape(10.dp))
    ) {

        AsyncImage(
            model = wallpaper,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            imageLoader = imageLoader,
            onSuccess = { showShimmer = false },
            modifier = modifier
                .fillMaxSize()
                .clickable { onWallpaperClick(wallpaper) }

        )


        Image(
            imageVector = Icons.Filled.Favorite,
            contentDescription = stringResource(id = R.string.favourite),
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier
                .size(30.dp)
                .padding(3.dp)
                .clip(CircleShape)
                .clickable { onRemoveFromFavClick(wallpaper) }
                .background(color = ActionIconBgColor)
                .padding(4.dp)
                .align(Alignment.BottomStart)


        )

    }


}