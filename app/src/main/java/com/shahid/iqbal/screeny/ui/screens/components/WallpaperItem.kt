package com.shahid.iqbal.screeny.ui.screens.components

import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.shahid.iqbal.screeny.models.Wallpaper
import com.shahid.iqbal.screeny.ui.theme.ActionIconBgColor
import com.shahid.iqbal.screeny.ui.utils.ComponentHelpers.noRippleClickable

@Composable
fun WallpaperItem(
    modifier: Modifier = Modifier,
    wallpaper: String,
    imageLoader: ImageLoader,
    getDrawable: ((Drawable) -> Unit)? = null,
    onWallpaperClick: (String) -> Unit = {}
) {

    var showShimmer by remember { mutableStateOf(true) }
    var loadedWallpaper: Drawable? by remember {
        mutableStateOf(null)
    }

    AsyncImage(
        model = wallpaper,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        imageLoader = imageLoader,
        onSuccess = { success ->
            showShimmer = false
            val drawable = success.result.drawable
            getDrawable?.invoke(drawable)
            loadedWallpaper = drawable

        },
        modifier = modifier
            .background(
                shimmerBrush(targetValue = 1300f, showShimmer = showShimmer), shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .height(200.dp)
            .fillMaxWidth()
            .noRippleClickable { onWallpaperClick(wallpaper) }

    )



}