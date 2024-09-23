package com.shahid.iqbal.screeny.ui.screens.category

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.size.Size
import coil.size.SizeResolver
import com.shahid.iqbal.screeny.models.Category
import com.shahid.iqbal.screeny.ui.screens.components.imageRequestBuilder
import kotlinx.coroutines.delay


@Composable
fun CategoryScreen(modifier: Modifier = Modifier) {

    var showContent by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        delay(100)
        showContent = true
    }

    if (!showContent) {
        CircularProgressIndicator(modifier = Modifier.wrapContentSize())
    }

    AnimatedVisibility(visible = showContent) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp), modifier = modifier.fillMaxSize()
        ) {
            items(categories, key = { it.name }) { category ->
                CategoryItem(category = category)
            }

        }
    }

}

@Composable
fun CategoryItem(category: Category) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .height(100.dp),
        contentAlignment = Alignment.Center,
    ) {

        AsyncImage(
            model = imageRequestBuilder(image = category.thumbnail)
                .build(),
            contentDescription = category.name, modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Black, Color(0xFF29323B))
                    ), alpha = 0.6f
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                text = category.name,
                style =
                MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                textAlign = TextAlign.Center,
            )
        }
    }
}