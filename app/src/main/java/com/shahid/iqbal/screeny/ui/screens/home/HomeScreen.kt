package com.shahid.iqbal.screeny.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.disk.DiskCache
import com.shahid.iqbal.screeny.models.Wallpaper
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    wallpaperViewModel: WallpaperViewModel = koinViewModel()
) {

    val wallpapers = wallpaperViewModel.getAllWallpapers.collectAsLazyPagingItems()

    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader(context)
            .newBuilder()
            .crossfade(true)
            .diskCache(DiskCache.Builder().directory(context.filesDir).build())
            .build()
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(90.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        if (wallpapers.loadState.refresh == LoadState.Loading) {
            item {
                CircularProgressIndicator(modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
            }
        } else if (wallpapers.loadState.refresh is LoadState.Error) {
            item {
                Text(text = "Error loading items: ${(wallpapers.loadState.refresh as LoadState.Error).error.localizedMessage}",
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
            }
        }

        items(wallpapers.itemCount) { index ->
            if (index < wallpapers.itemCount) {
                val wallpaper = wallpapers[index]
                if (wallpaper != null) {
                    WallpaperItem(wallpaper = wallpaper, imageLoader)
                }
            }
        }

        if (wallpapers.loadState.append == LoadState.Loading) {
            item { CircularProgressIndicator(modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)) }
        }
    }
}

@Composable
fun WallpaperItem(wallpaper: Wallpaper, imageLoader: ImageLoader) {
    AsyncImage(
        model = wallpaper.wallpaperSource.portrait,
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .clip(RoundedCornerShape(10.dp)),
        contentDescription = null,
        imageLoader = imageLoader,

        )
}