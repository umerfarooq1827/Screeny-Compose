package com.shahid.iqbal.screeny.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import com.shahid.iqbal.screeny.ui.screens.components.LoadingPlaceHolder
import com.shahid.iqbal.screeny.ui.screens.components.WallpaperItem
import com.shahid.iqbal.screeny.ui.theme.screenyFontFamily
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun SearchedWallpaperScreen(title: String, searchedWallpaperViewModel: SearchWallpaperViewModel = koinViewModel()) {

    val wallpapers = searchedWallpaperViewModel.searchWallpapers(title).collectAsLazyPagingItems()
    val imageLoader: ImageLoader = koinInject()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ToolBar(title = title)


        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize(),
        ) {

            if (wallpapers.loadState.refresh == LoadState.Loading
            ) {
                items(20) {
                    LoadingPlaceHolder()
                }
            } else if (wallpapers.loadState.append == LoadState.Loading) {
                item {
                    LoadingPlaceHolder()
                }
            } else {
                items(wallpapers.itemCount) { index ->
                    if (index < wallpapers.itemCount) {
                        val wallpaper = wallpapers[index]
                        if (wallpaper != null) {
                            WallpaperItem(wallpaper = wallpaper, imageLoader)
                        }
                    }
                }
            }

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(title: String, modifier: Modifier = Modifier) {
    TopAppBar(title = {
        Text(
            text = title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), fontFamily = screenyFontFamily,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            textAlign = TextAlign.Start

        )
    }, navigationIcon = {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }, modifier = modifier.height(70.dp)
    )
}