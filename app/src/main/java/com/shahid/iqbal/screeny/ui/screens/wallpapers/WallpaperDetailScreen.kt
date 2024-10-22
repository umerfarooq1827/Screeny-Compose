package com.shahid.iqbal.screeny.ui.screens.wallpapers

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.models.FavouriteWallpaper
import com.shahid.iqbal.screeny.models.Wallpaper
import com.shahid.iqbal.screeny.ui.screens.components.ActionButtons
import com.shahid.iqbal.screeny.ui.screens.components.BlurBg
import com.shahid.iqbal.screeny.ui.screens.components.SinglePageContent
import com.shahid.iqbal.screeny.ui.shared.SharedWallpaperViewModel
import com.shahid.iqbal.screeny.ui.utils.ComponentHelpers.SetStatusBarBarColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun WallpaperDetailScreen(
    sharedWallpaperViewModel: SharedWallpaperViewModel,
    actionViewModel: ActionViewModel = koinViewModel(),
    isFromFavourite: Boolean = false,
    onBack: () -> Unit
) {


    val wallpapers by sharedWallpaperViewModel.wallpaperList.collectAsStateWithLifecycle()
    val index by sharedWallpaperViewModel.selectedWallpaperIndex.collectAsStateWithLifecycle()
    val favouriteList by actionViewModel.getAllFavourites.collectAsStateWithLifecycle()
    val currentlyLoadedWallpaper by sharedWallpaperViewModel.currentWallpaper.collectAsStateWithLifecycle(initialValue = null)
    val luminanceResults by sharedWallpaperViewModel.luminanceResults.collectAsStateWithLifecycle()

    var canShowDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current


    val imageLoader = koinInject<ImageLoader>()
    val pagerState = rememberPagerState(initialPage = if (index != -1) index else 0)
    { if (isFromFavourite) favouriteList.size else wallpapers.size }

    var canShowList by remember { mutableStateOf(false) }
    var isFavourite by remember { mutableStateOf(false) }
    var iconColorIsBlack by remember { mutableStateOf(true) }


    val iconColor by animateColorAsState(
        targetValue = if (iconColorIsBlack) Color.Black else Color.White,
        label = "Back Button and StatusBar Color",
    )

    LaunchedEffect(key1 = canShowList) {
        delay(100)
        canShowList = true
    }


    LaunchedEffect(key1 = Unit) {

        val initialWallpaper = if (isFromFavourite) favouriteList[pagerState.currentPage].wallpaper
        else wallpapers[pagerState.currentPage].wallpaperSource.portrait
        iconColorIsBlack = luminanceResults[initialWallpaper] ?: false



        snapshotFlow {
            val wallpaper = if (isFromFavourite) favouriteList[pagerState.currentPage].wallpaper
            else wallpapers[pagerState.currentPage].wallpaperSource.portrait

            luminanceResults[wallpaper]
        }.filterNotNull().collectLatest { isLight ->
            iconColorIsBlack = isLight
        }
    }


    SetStatusBarBarColor(iconColorIsBlack)


    if (!canShowList) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp), strokeWidth = 4.dp, strokeCap = StrokeCap.Round
            )
        }
    }

    AnimatedVisibility(
        visible = canShowList, modifier = Modifier
            .fillMaxSize()

    ) {

        Box(contentAlignment = Alignment.BottomCenter) {


            BlurBg(
                if (isFromFavourite) favouriteList[pagerState.currentPage].wallpaper
                else wallpapers[pagerState.currentPage].wallpaperSource.small
            )


            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .safeDrawingPadding()
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .zIndex(90f)
                    .clickable { onBack() },
                tint = iconColor
            )


            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                beyondViewportPageCount = 3,
                key = { if (isFromFavourite) favouriteList[it].id else wallpapers[it].id },
            ) { page ->


                if (page == pagerState.currentPage) {
                    isFavourite = if (isFromFavourite) true else {
                        favouriteList.any { it.id == wallpapers[pagerState.currentPage].id }
                    }
                }

                SinglePageContent(
                    wallpaperUrl = if (isFromFavourite) favouriteList[page].wallpaper else wallpapers[page].wallpaperSource.portrait,
                    imageLoader = imageLoader,
                    pagerState = pagerState,
                    page = page,
                    updateWallpaper = sharedWallpaperViewModel::updateWallpaper,
                    updateLuminanceResult = sharedWallpaperViewModel::performLuminanceWork
                )
            }


            ActionButtons(isFavourite = isFavourite, onDownload = {
                downloadWallpaper(actionViewModel, isFromFavourite, favouriteList, pagerState, wallpapers, context)
            }, onApply = {
                canShowDialog = true
            }, onFavourite = {

                val wallpaper = wallpapers[pagerState.settledPage]
                actionViewModel.addOrRemove(wallpaper = wallpaper)
                isFavourite = favouriteList.any { it.id == wallpaper.id }
            })
        }

        if (canShowDialog)
            WallpaperApplyDialog(wallpaper = currentlyLoadedWallpaper, onDismissRequest = { canShowDialog = false })

    }

}


private fun downloadWallpaper(
    actionViewModel: ActionViewModel,
    isFromFavourite: Boolean,
    favouriteList: List<FavouriteWallpaper>,
    pagerState: PagerState,
    wallpapers: List<Wallpaper>,
    context: Context
) {
    actionViewModel.downloadWallpaper(
        url = if (isFromFavourite) favouriteList[pagerState.currentPage].wallpaper
        else wallpapers[pagerState.currentPage].wallpaperSource.portrait
    )
    Toast.makeText(context, context.getString(R.string.downloading), Toast.LENGTH_SHORT).show()
}


