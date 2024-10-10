package com.shahid.iqbal.screeny.ui.screens.wallpapers

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.models.FavouriteWallpaper
import com.shahid.iqbal.screeny.models.Wallpaper
import com.shahid.iqbal.screeny.ui.screens.components.ActionButtons
import com.shahid.iqbal.screeny.ui.screens.components.BlurBg
import com.shahid.iqbal.screeny.ui.screens.components.SinglePageContent
import com.shahid.iqbal.screeny.ui.shared.SharedWallpaperViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun WallpaperDetailScreen(
    sharedWallpaperViewModel: SharedWallpaperViewModel, isFromFavourite: Boolean = false
) {

    val actionViewModel = koinViewModel<ActionViewModel>()

    val wallpapers by sharedWallpaperViewModel.wallpaperList.collectAsStateWithLifecycle()
    val index by sharedWallpaperViewModel.selectedWallpaperIndex.collectAsStateWithLifecycle()
    val favouriteList by actionViewModel.getAllFavourites.collectAsStateWithLifecycle()
    var canShowDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current


    val imageLoader = koinInject<ImageLoader>()
    val pagerState = rememberPagerState(initialPage = if (index != -1) index else 0)
    { if (isFromFavourite) favouriteList.size else wallpapers.size }

    var canShowList by remember { mutableStateOf(false) }
    var isFavourite by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = canShowList) {
        delay(300)
        canShowList = true
    }


    if (!canShowList) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp), strokeWidth = 4.dp, strokeCap = StrokeCap.Round
            )
        }
    }

    AnimatedVisibility(visible = canShowList) {

        Box(contentAlignment = Alignment.BottomCenter) {

            BlurBg(if (isFromFavourite) favouriteList[pagerState.currentPage].wallpaper else wallpapers[pagerState.currentPage].wallpaperSource.small)

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                key = { if (isFavourite) "${favouriteList[it].id}_wallpaper" else "${wallpapers[it].id}__wallpaper" },
            ) { page ->


                if (page == pagerState.currentPage) {
                    isFavourite = if (isFromFavourite) true else {
                        favouriteList.any { it.id == wallpapers[pagerState.currentPage].id }
                    }
                }

                SinglePageContent(
                    wallpaperUrl = if (isFromFavourite) favouriteList[page].wallpaper else wallpapers[page].wallpaperSource.portrait, imageLoader = imageLoader, pagerState, page
                )
            }


            ActionButtons(isFavourite = isFavourite, onDownload = {
                downloadWallpaper(actionViewModel, isFromFavourite, favouriteList, pagerState, wallpapers, context)
            }, onApply = {
                canShowDialog = true
            }, onFavourite = {

                val wallpaper = wallpapers[pagerState.currentPage]
                actionViewModel.addOrRemove(wallpaper = wallpaper)
                isFavourite = favouriteList.any { it.id == wallpaper.id }
            })
        }

        if (canShowDialog)
            WallpaperApplyDialog(onDismissRequest = { canShowDialog = false })

    }

}

private fun downloadWallpaper(
    actionViewModel: ActionViewModel, isFromFavourite: Boolean, favouriteList: List<FavouriteWallpaper>, pagerState: PagerState, wallpapers: List<Wallpaper>, context: Context
) {
    actionViewModel.downloadWallpaper(
        url = if (isFromFavourite) favouriteList[pagerState.currentPage].wallpaper
        else wallpapers[pagerState.currentPage].wallpaperSource.portrait
    )
    Toast.makeText(context, context.getString(R.string.downloading), Toast.LENGTH_SHORT).show()
}


