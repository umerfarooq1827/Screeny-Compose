package com.shahid.iqbal.screeny.ui.screens.splash

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.ui.routs.Routs
import com.shahid.iqbal.screeny.ui.theme.GrayColor
import com.shahid.iqbal.screeny.ui.theme.SplashColor
import com.shahid.iqbal.screeny.ui.utils.ComponentHelpers.HideSystemBars
import com.shahid.iqbal.screeny.utils.Extensions.debug
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navController: NavController, modifier: Modifier = Modifier
) {

    val splashViewModel = koinViewModel<SplashViewModel>()
    val progress by splashViewModel.progress.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val imageLoader = remember { ImageLoader(context) }
    val splashTitle = remember {
        buildAnnotatedString {
            pushStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 29.sp, color = Color.White))
            append(context.getString(R.string.app_name))
            pushStyle(SpanStyle(fontWeight = FontWeight.Light, fontSize = 24.sp, color = Color.White))
            append(" Wallpaper")
        }
    }

    context.HideSystemBars(true)

    LaunchedEffect(key1 = progress) {
        if (progress >= 1f)
            navController.navigate(Routs.Home)

    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SplashColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            modifier = Modifier.size(100.dp),
            model = R.drawable.app_logo,
            contentScale = ContentScale.Fit,
            contentDescription = null, imageLoader = imageLoader
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = splashTitle)

        Spacer(modifier = Modifier.height(20.dp))

        LinearProgressIndicator(
            modifier = Modifier
                .height(6.dp)
                .fillMaxWidth(0.4f),
            color = Color.White,
            trackColor = GrayColor,
            strokeCap = Round,
            progress = { progress },
            gapSize = 0.dp
        )
    }


}