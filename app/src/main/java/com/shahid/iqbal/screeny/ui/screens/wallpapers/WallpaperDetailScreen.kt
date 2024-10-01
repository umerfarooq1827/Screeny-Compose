package com.shahid.iqbal.screeny.ui.screens.wallpapers

import BlurTransformation
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.models.Wallpaper
import com.shahid.iqbal.screeny.ui.screens.components.WallpaperItem
import com.shahid.iqbal.screeny.ui.shared.SharedWallpaperViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import kotlin.math.absoluteValue

@Composable
fun WallpaperDetailScreen(
    sharedWallpaperViewModel: SharedWallpaperViewModel
) {


    val wallpapers by sharedWallpaperViewModel.wallpaperList.collectAsStateWithLifecycle()
    val selectedWallpaper by sharedWallpaperViewModel.selectedWallpaper.collectAsStateWithLifecycle()
    val index by remember { mutableIntStateOf(wallpapers.indexOf(selectedWallpaper)) }
    val actionViewModel = koinViewModel<ActionViewModel>()

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
                model = ImageRequest.Builder(LocalContext.current).data(wallpapers[pagerState.currentPage].wallpaperSource.portrait).crossfade(true).transformations(
                    listOf(
                        BlurTransformation(
                            scale = 0.5f, radius = 25
                        )
                    )
                ).build(), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.7f),
                                Color.Transparent
                            )
                        )
                    )
            ) {}

            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(horizontal = 20.dp), key = { wallpapers[it].id }) { page ->

                val wallpaper = wallpapers[page]

                SinglePageContent(wallpaper = wallpaper, imageLoader = imageLoader, pagerState, page, onDownload = {}, onApply = {}, onFavourite = { actionViewModel.addOrRemove(wallpaper) })
            }
        }


    }

}

@Composable
private fun SinglePageContent(
    wallpaper: Wallpaper, imageLoader: ImageLoader, pagerState: PagerState, page: Int, onDownload: () -> Unit, onApply: () -> Unit, onFavourite: () -> Unit
) {

    Box(
        modifier = Modifier
            .carouselTransition(page, pagerState)
            .padding(10.dp), contentAlignment = Alignment.Center
    ) {


        WallpaperItem(
            wallpaper = wallpaper, imageLoader = imageLoader, modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
        ) {}

        ActionButtons(
            onDownload = onDownload, onApply = onApply, onFavourite = onFavourite
        )


    }
}

@Composable
private fun ActionButtons(onDownload: () -> Unit = {}, onApply: () -> Unit = {}, onFavourite: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {


        Image(painter = painterResource(id = R.drawable.download_icon), contentDescription = null, modifier = Modifier
            .size(50.dp)
            .background(color = Color(0xFF191E31).copy(alpha = 0.53f), shape = CircleShape)
            .padding(7.dp)
            .clickable { onDownload() })

        Image(painter = painterResource(id = R.drawable.group_191), contentDescription = null, modifier = Modifier
            .size(50.dp)
            .background(color = Color.White, shape = CircleShape)
            .padding(5.dp)
            .clickable { onApply() })

        Image(painterResource(id = R.drawable.favourite_unchecked), contentDescription = null, modifier = Modifier.clickable { onFavourite() })


    }
}

fun Modifier.carouselTransition(page: Int, pagerState: PagerState) = graphicsLayer {
    val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

    val transformation = lerp(
        start = 0.7f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    alpha = transformation
    scaleY = transformation
}
