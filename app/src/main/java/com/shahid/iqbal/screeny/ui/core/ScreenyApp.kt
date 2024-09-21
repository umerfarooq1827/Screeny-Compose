package com.shahid.iqbal.screeny.ui.core

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.ui.routs.Routs
import com.shahid.iqbal.screeny.ui.routs.Routs.Category
import com.shahid.iqbal.screeny.ui.routs.Routs.Favourite
import com.shahid.iqbal.screeny.ui.routs.Routs.Home
import com.shahid.iqbal.screeny.ui.routs.Routs.Setting
import com.shahid.iqbal.screeny.ui.routs.Routs.Splash
import com.shahid.iqbal.screeny.ui.routs.bottomNavigationItems
import com.shahid.iqbal.screeny.ui.screens.components.BottomNavigationBar
import com.shahid.iqbal.screeny.ui.screens.components.ManageBarVisibility
import com.shahid.iqbal.screeny.ui.screens.components.TopBar
import com.shahid.iqbal.screeny.ui.screens.home.HomeScreen
import com.shahid.iqbal.screeny.ui.screens.home.WallpaperViewModel
import com.shahid.iqbal.screeny.ui.screens.splash.SplashScreen
import com.shahid.iqbal.screeny.utils.Extensions.debug
import org.koin.androidx.compose.koinViewModel


@Composable
fun ScreenyApp(navController: NavHostController) {


    var canShowBottomBar by rememberSaveable { mutableStateOf(false) }
    var canShowTopBar by rememberSaveable { mutableStateOf(false) }
    val wallpaperViewModel: WallpaperViewModel = koinViewModel()
    val stackEntry by navController.currentBackStackEntryAsState()


    ManageBarVisibility(
        navController = navController,
        showTopBar = { canShowTopBar = it },
        showBottomBar = { canShowBottomBar = it }
    )

    Scaffold(
        bottomBar = {
            if (canShowBottomBar) BottomNavigationBar(navController)
        },
        topBar = {
            if (canShowTopBar) {
                val title = stackEntry?.destination?.route?.substringAfterLast(".")
                    ?: stringResource(id = R.string.app_name)
                TopBar(title = title)
            }
        }
    ) { innerPadding ->

        NavHost(
            navController, startDestination = Splash,
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            composable<Splash> {
                SplashScreen(navController = navController)
            }

            composable<Home> {
                HomeScreen(wallpaperViewModel)
            }

            composable<Category> {}

            composable<Favourite> {}

            composable<Setting> {}

        }
    }
}





