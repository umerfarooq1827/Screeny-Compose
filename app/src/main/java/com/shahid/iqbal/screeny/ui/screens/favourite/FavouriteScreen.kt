package com.shahid.iqbal.screeny.ui.screens.favourite

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.ImageLoader
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.ui.routs.Routs
import com.shahid.iqbal.screeny.ui.screens.components.WallpaperItem
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FavouriteScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController,
    onWallpaperClick: (String) -> Unit
) {

    val favouriteViewModel = koinViewModel<FavouriteViewModel>()
    val favourites by favouriteViewModel.getAllFavourites.collectAsStateWithLifecycle(emptyList())
    val imageLoader = koinInject<ImageLoader>()


    if (favourites.isEmpty()) {
        NoFavouritePlaceholder(onExplore = { navController.navigate(Routs.Home) })
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.fillMaxSize(),
        ) {

            items(favourites, key = { favourite -> favourite.timeStamp }) { favourite ->
                FavouriteWallpaperItem(
                    wallpaper = favourite.wallpaper, imageLoader = imageLoader,
                    animatedVisibilityScope = animatedVisibilityScope
                ) { wallpaper -> onWallpaperClick(wallpaper) }
            }

        }
    }


}

@Composable
fun NoFavouritePlaceholder(onExplore: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_favourite_found),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
            modifier = Modifier.size(80.dp)
        )

        Text(
            text = "Your favorite wallpapers will appear here!\nStart exploring and add some to your favorites!",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f), // Adjusted alpha for better visibility
            style = MaterialTheme.typography.bodyMedium, // Use a more readable text style
            textAlign = TextAlign.Center, // Center align the text
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 20.dp)
        )

        Button(
            onClick = onExplore,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Explore Wallpapers")
        }
    }
}


