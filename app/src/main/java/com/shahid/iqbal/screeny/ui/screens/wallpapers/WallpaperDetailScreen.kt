package com.shahid.iqbal.screeny.ui.screens.wallpapers

import BlurTransformation
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shahid.iqbal.screeny.models.Wallpaper
import com.shahid.iqbal.screeny.ui.screens.components.WallpaperItem
import com.shahid.iqbal.screeny.ui.shared.SharedWallpaperViewModel
import kotlinx.coroutines.delay
import org.koin.compose.koinInject
import kotlin.math.absoluteValue

@Composable
fun WallpaperDetailScreen(
    sharedWallpaperViewModel: SharedWallpaperViewModel
) {


    val wallpapers by sharedWallpaperViewModel.wallpaperList.collectAsStateWithLifecycle()
    val selectedWallpaper by sharedWallpaperViewModel.selectedWallpaper.collectAsStateWithLifecycle()
    val index by remember { mutableIntStateOf(wallpapers.indexOf(selectedWallpaper)) }

    val imageLoader = koinInject<ImageLoader>()
    val pagerState = rememberPagerState(initialPage = if (index != -1) index else 0) { wallpapers.size }

    var canShowList by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = canShowList) {
        delay(200)
        canShowList = true
    }


    AnimatedVisibility(visible = canShowList) {

        Box {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(wallpapers[pagerState.currentPage].wallpaperSource.portrait)
                    .crossfade(true)
                    .transformations(
                        listOf(
                            BlurTransformation(
                                scale = 0.5f,
                                radius = 25
                            )
                        )
                    )
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.7f), // Top part of the shadow
                                Color.Transparent // Bottom part (fading out)
                            )
                        )
                    )
            ) {}

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                key = { wallpapers[it].id }
            ) { page ->

                val wallpaper = wallpapers[page]

                SinglePageContent(wallpaper = wallpaper, imageLoader = imageLoader, pagerState, page) {}
            }
        }


    }

}

@Composable
private fun SinglePageContent(
    wallpaper: Wallpaper, imageLoader: ImageLoader, pagerState: PagerState, page: Int, onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .carouselTransition(page, pagerState)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {


        WallpaperItem(
            wallpaper = wallpaper,
            imageLoader = imageLoader,
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth(),

            onClick
        )

    }
}

fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation =
            lerp(
                start = 0.7f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        alpha = transformation
        scaleY = transformation
    }
