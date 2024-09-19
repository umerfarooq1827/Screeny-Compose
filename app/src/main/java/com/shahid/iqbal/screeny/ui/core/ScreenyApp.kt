package com.shahid.iqbal.screeny.ui.core

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shahid.iqbal.screeny.ui.routs.Routs.Category
import com.shahid.iqbal.screeny.ui.routs.Routs.Favourite
import com.shahid.iqbal.screeny.ui.routs.Routs.Home
import com.shahid.iqbal.screeny.ui.routs.Routs.Setting
import com.shahid.iqbal.screeny.ui.routs.Routs.Splash
import com.shahid.iqbal.screeny.ui.routs.bottomNavigationItems
import com.shahid.iqbal.screeny.ui.screens.home.HomeScreen
import com.shahid.iqbal.screeny.ui.screens.splash.SplashScreen


@Composable
fun ScreenyApp(navController: NavHostController) {


    var canShowBottomBar by rememberSaveable { mutableStateOf(false) }
    Scaffold(bottomBar = { if (canShowBottomBar) BottomNav(navController) }) { innerPadding ->

        NavHost(
            navController, startDestination = Splash,
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            composable<Splash> {
                canShowBottomBar = false
                SplashScreen(navController = navController)
            }

            composable<Home> {
                canShowBottomBar = true
                HomeScreen()
            }

            composable<Category> {
                canShowBottomBar = true
            }

            composable<Favourite> {
                canShowBottomBar = true
            }

            composable<Setting> {
                canShowBottomBar = true
            }

        }
    }
}


@SuppressLint("RestrictedApi")
@Composable
fun BottomNav(navController: NavHostController) {
    var selectIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        bottomNavigationItems.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(selected = selectIndex == index, onClick = {
                selectIndex = index
                navController.navigate(bottomNavItem.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // re-selecting the same item
                    launchSingleTop = true
                    // Restore state when re-selecting a previously selected item
                    restoreState = true
                }
            }, icon = {
                Icon(
                    bottomNavItem.icon,
                    contentDescription = stringResource(id = bottomNavItem.name)
                )
            }, label = { Text(stringResource(id = bottomNavItem.name)) }, alwaysShowLabel = false
            )
        }
    }
}

