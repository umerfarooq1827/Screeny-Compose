package com.shahid.iqbal.screeny.ui.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.shahid.iqbal.screeny.ui.routs.Routs.Splash

@Composable
fun ManageBarVisibility(navController: NavHostController, showTopBar: (Boolean) -> Unit, showBottomBar: (Boolean) -> Unit) {
    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _: NavController, destination: NavDestination, _ ->
            when (destination.route) {
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

        // Add the listener
        navController.addOnDestinationChangedListener(listener)

        // Remove the listener when the composable is removed from the composition
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}

