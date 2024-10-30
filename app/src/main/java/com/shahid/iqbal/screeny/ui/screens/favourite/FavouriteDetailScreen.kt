package com.shahid.iqbal.screeny.ui.screens.favourite

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shahid.iqbal.screeny.ui.screens.wallpapers.ActionViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FavouriteDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    wallpaper: String
) {



    BackHandler { navController.navigateUp() }

    val imageLoader = koinInject<ImageLoader>()
    val actionViewModel = koinViewModel<ActionViewModel>()

    Column(modifier = modifier.fillMaxSize()) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(wallpaper)
                .crossfade(false)
                .placeholderMemoryCacheKey("image-$wallpaper")
                .memoryCacheKey("image-$wallpaper")
                .build(),
            placeholder = null,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            imageLoader = imageLoader,
            modifier = Modifier
                .sharedElement(
                    rememberSharedContentState(
                        key = "image-$wallpaper"
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
                .fillMaxSize()

        )


    }
}