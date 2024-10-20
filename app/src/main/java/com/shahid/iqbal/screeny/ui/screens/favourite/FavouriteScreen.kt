package com.shahid.iqbal.screeny.ui.screens.favourite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.ImageLoader
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.ui.routs.Routs
import com.shahid.iqbal.screeny.ui.screens.components.WallpaperItem
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun FavouriteScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val favouriteViewModel = koinViewModel<FavouriteViewModel>()
    val favourites by favouriteViewModel.getAllFavourites.collectAsStateWithLifecycle()
    val imageLoader = koinInject<ImageLoader>()

    if (favourites.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.fillMaxSize(),
        ) {

            items(favourites, key = { favourite -> favourite.timeStamp }) { favourite ->
                WallpaperItem(wallpaper = favourite.wallpaper, imageLoader)
            }

        }
    } else {
        NoFavouritePlaceholder(onExplore = { navController.navigate(Routs.Home) })
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

        // Optionally, you can add a button to encourage users to start exploring
        Button(
            onClick = onExplore,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Explore Wallpapers")
        }
    }
}


