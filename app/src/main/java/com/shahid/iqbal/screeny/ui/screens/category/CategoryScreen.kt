package com.shahid.iqbal.screeny.ui.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.shahid.iqbal.screeny.models.Category
import com.shahid.iqbal.screeny.ui.theme.screenyFontFamily
import com.shahid.iqbal.screeny.utils.Extensions.toPx
import kotlinx.coroutines.delay
import org.koin.compose.koinInject


@Composable
fun CategoryScreen(modifier: Modifier = Modifier, onCategoryClick: (String) -> Unit) {

    var showContent by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = Unit) {
        delay(300)
        showContent = true
    }

    val imageLoader: ImageLoader = koinInject()

    if (!showContent) {
        CircularProgressIndicator(modifier = Modifier.wrapContentSize())
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp), modifier = modifier.fillMaxSize()
        ) {
            items(categories, key = { it.name }) { category ->
                CategoryItem(category = category, imageLoader) {
                    onCategoryClick(category.name)
                }
            }

        }
    }


}


@Composable
fun CategoryItem(category: Category, imageLoader: ImageLoader, onClick: () -> Unit) {

    val context = LocalContext.current
    val categoryImageSize = Size(800.dp.toPx().toInt(), 800.dp.toPx().toInt())
    val imageRequest = remember {
        ImageRequest.Builder(context)
            .data(category.thumbnail)
            .size(categoryImageSize)
            .build()
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .height(100.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {

        AsyncImage(
            model = imageRequest,
            contentDescription = category.name,
            imageLoader = imageLoader,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
        )


        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Black, Color(0xFF29323B))
                    ), alpha = 0.45f
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                text = category.name,
                style =
                MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold, fontSize = 20.sp,
                    fontFamily = screenyFontFamily
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}