package com.shahid.iqbal.screeny.ui.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shahid.iqbal.screeny.ui.routs.Routs.Splash

@Composable
fun ManageBarVisibility(currentEntry: NavBackStackEntry?, showTopBar: (Boolean) -> Unit, showBottomBar: (Boolean) -> Unit) {
    currentEntry?.let { entry ->
        when (entry.destination.route) {
            Splash::class.qualifiedName -> {
                showTopBar(false)
                showBottomBar(false)
            }

            else -> {
                showTopBar(true)
                showBottomBar(true)
            }
        }
    }
}

