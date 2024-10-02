package com.shahid.iqbal.screeny.ui.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.models.Wallpaper
import com.shahid.iqbal.screeny.ui.screens.components.LoadingPlaceHolder
import com.shahid.iqbal.screeny.ui.screens.components.WallpaperItem
import com.shahid.iqbal.screeny.ui.screens.components.shimmerBrush
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    wallpaperViewModel: WallpaperViewModel, modifier: Modifier = Modifier, onWallpaperClick: (Int, List<Wallpaper>) -> Unit, onBack: () -> Unit
) {

    val wallpapers = wallpaperViewModel.getAllWallpapers.collectAsLazyPagingItems()
    val imageLoader: ImageLoader = koinInject()

    BackHandler { onBack() }


    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize(),
    ) {

        if (wallpapers.loadState.refresh == LoadState.Loading) {
            items(20) {
                LoadingPlaceHolder(modifier = Modifier.height(200.dp))
            }
        } else {
            items(wallpapers.itemCount, key = { wallpapers[it]?.createdAt ?: wallpapers.hashCode() }) { index ->
                if (index < wallpapers.itemCount) {
                    val wallpaper = wallpapers[index]
                    if (wallpaper != null) {
                        WallpaperItem(wallpaper = wallpaper.wallpaperSource.portrait, imageLoader) {
                            onWallpaperClick(wallpapers.itemSnapshotList.items.indexOf(wallpaper), wallpapers.itemSnapshotList.items)
                        }
                    }
                }
            }
        }

    }
}


