package com.shahid.iqbal.screeny.ui.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.models.Wallpaper
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    wallpaperViewModel: WallpaperViewModel,
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {

    val wallpapers = wallpaperViewModel.getAllWallpapers.collectAsLazyPagingItems()

    BackHandler { onBack() }


    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize(),
    ) {

        items(wallpapers.itemCount) { index ->
            if (index < wallpapers.itemCount) {
                val wallpaper = wallpapers[index]
                if (wallpaper != null) {
                    WallpaperItem(wallpaper = wallpaper)
                }
            }
        }
    }
}

@Composable
fun WallpaperItem(wallpaper: Wallpaper) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(wallpaper.wallpaperSource.portrait)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(
                RoundedCornerShape(10.dp)
            )
            .wrapContentSize()
    )
}