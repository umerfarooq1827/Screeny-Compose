package com.shahid.iqbal.screeny.ui.screens.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import com.shahid.iqbal.screeny.ui.screens.components.WallpaperItem
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun FavouriteScreen(modifier: Modifier = Modifier) {

    val favouriteViewModel = koinViewModel<FavouriteViewModel>()
    val favourites by favouriteViewModel.getAllFavourites.collectAsStateWithLifecycle()
    val imageLoader = koinInject<ImageLoader>()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize(),
    ) {

        items(favourites, key = { favourite -> favourite.timeStamp }) { favourite ->
            WallpaperItem(wallpaper = favourite.wallpaper, imageLoader, getDrawable = {})
        }

    }
}

