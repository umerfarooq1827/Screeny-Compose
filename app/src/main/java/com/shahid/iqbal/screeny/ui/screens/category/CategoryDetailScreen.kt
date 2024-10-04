package com.shahid.iqbal.screeny.ui.screens.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import com.shahid.iqbal.screeny.models.Wallpaper
import com.shahid.iqbal.screeny.ui.screens.components.LoadingPlaceHolder
import com.shahid.iqbal.screeny.ui.screens.components.LocalNavController
import com.shahid.iqbal.screeny.ui.screens.components.WallpaperItem
import com.shahid.iqbal.screeny.ui.theme.screenyFontFamily
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun CategoryDetailScreen(
    title: String,
    categoryViewModel: CategoryViewModel,
    onWallpaperClick: (Int, List<Wallpaper>) -> Unit
) {

    val wallpapers = categoryViewModel.searchWallpapers(title).collectAsLazyPagingItems()
    val imageLoader: ImageLoader = koinInject()
    val navController = LocalNavController.current

    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ToolBar(title = title) {
            navController.navigateUp()
        }


        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize(),
        ) {

            if (wallpapers.loadState.refresh == LoadState.Loading) {
                items(20) {
                    LoadingPlaceHolder(modifier = Modifier.height(200.dp))
                }
            } else {
                items(wallpapers.itemCount, key = { wallpapers[it]?.id ?: wallpapers.hashCode() }) { index ->
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
}

@Composable
fun ToolBar(title: String, modifier: Modifier = Modifier, onBackClick: () -> Unit) {

    Row(
        modifier = modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(start = 10.dp),
             horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier
                .wrapContentSize()
                .clickable { onBackClick() }


        )

        Text(
            text = title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            fontFamily = screenyFontFamily, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(end = 30.dp),
            textAlign = TextAlign.Center

        )
    }

}