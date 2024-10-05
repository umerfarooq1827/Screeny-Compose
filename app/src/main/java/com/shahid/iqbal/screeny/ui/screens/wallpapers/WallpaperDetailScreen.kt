package com.shahid.iqbal.screeny.ui.screens.wallpapers

import BlurTransformation
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.shahid.iqbal.screeny.ui.theme.ActionIconBgColor
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import kotlin.math.absoluteValue

@Composable
fun WallpaperDetailScreen(
    sharedWallpaperViewModel: SharedWallpaperViewModel
) {

    val actionViewModel = koinViewModel<ActionViewModel>()

    val wallpapers by sharedWallpaperViewModel.wallpaperList.collectAsStateWithLifecycle()
    val index by sharedWallpaperViewModel.selectedWallpaperIndex.collectAsStateWithLifecycle()
    val favouriteList by actionViewModel.getAllFavourites.collectAsStateWithLifecycle()


    val imageLoader = koinInject<ImageLoader>()
    val pagerState = rememberPagerState(initialPage = if (index != -1) index else 0) { wallpapers.size }

    var canShowList by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = canShowList) {
        delay(300)
        canShowList = true
    }


    if (!canShowList) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp),
                strokeWidth = 4.dp, strokeCap = StrokeCap.Round
            )
        }
    }

    AnimatedVisibility(visible = canShowList) {

        Box {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(wallpapers[pagerState.currentPage].wallpaperSource.small)
                    .crossfade(true)
                    .transformations(BlurTransformation(scale = 0.5f, radius = 15))
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
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
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                key = { "${wallpapers[it].id}_$it" },
            ) { page ->

                val wallpaper = wallpapers[page]
                val isFavourite = if (page == pagerState.currentPage) {
                    favouriteList.any { it.id == wallpaper.id }
                } else false

                SinglePageContent(
                    wallpaper = wallpaper,
                    imageLoader = imageLoader,
                    pagerState,
                    page,
                    isFavourite = isFavourite,
                    onDownload = {},
                    onApply = {},
                    onFavourite = { actionViewModel.addOrRemove(wallpaper) }
                )
            }
        }


    }

}

@Composable
private fun SinglePageContent(
    wallpaper: Wallpaper,
    imageLoader: ImageLoader,
    pagerState: PagerState,
    page: Int,
    isFavourite: Boolean,
    onDownload: () -> Unit,
    onApply: () -> Unit,
    onFavourite: () -> Unit
) {

    Box(
        modifier = Modifier
            .carouselTransition(page, pagerState)
            .padding(10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {


        WallpaperItem(
            wallpaper = wallpaper.wallpaperSource.portrait, imageLoader = imageLoader, modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
        ) {}

        ActionButtons(
            isFavourite = isFavourite,
            onDownload = onDownload,
            onApply = onApply,
            onFavourite = onFavourite
        )


    }
}

@Composable
private fun ActionButtons(
    isFavourite: Boolean = false,
    onDownload: () -> Unit = {},
    onApply: () -> Unit = {},
    onFavourite: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .wrapContentHeight()
            .padding(horizontal = 10.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {


        Image(
            painter = painterResource(id = R.drawable.download_icon),
            contentDescription = stringResource(id = R.string.download),
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier
                .size(40.dp)
                .clickable { onDownload() }
                .clip(CircleShape)
                .background(color = ActionIconBgColor)
                .padding(8.dp)
        )

        Image(painter = painterResource(id = R.drawable.apply_icon),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable { onApply() }
                .background(color = Color.White))

        Image(
            imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = stringResource(id = R.string.favourite),
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable { onFavourite() }
                .background(color = ActionIconBgColor)
                .padding(8.dp)
        )


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
