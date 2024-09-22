package com.shahid.iqbal.screeny.ui.screens.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.shahid.iqbal.screeny.ui.routs.IconType
import com.shahid.iqbal.screeny.ui.routs.bottomNavigationItems

@SuppressLint("RestrictedApi")
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    var selectIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        bottomNavigationItems.forEachIndexed { index, bottomNavItem ->

            val isSelected = (selectIndex == index)

            NavigationBarItem(selected = isSelected, onClick = {
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
                when (bottomNavItem.icon) {
                    is IconType.Drawable -> {
                        Icon(
                            painter = painterResource(
                                id =
                                if (isSelected && bottomNavItem.selectedIcon is IconType.Drawable) bottomNavItem.selectedIcon.iconRes
                                else bottomNavItem.icon.iconRes
                            ), contentDescription = stringResource(id = bottomNavItem.name)
                        )
                    }

                    is IconType.Vector -> {
                        Icon(
                            imageVector = if (isSelected && bottomNavItem.selectedIcon is IconType.Vector) bottomNavItem.selectedIcon.icon
                            else bottomNavItem.icon.icon, contentDescription = stringResource(id = bottomNavItem.name)
                        )
                    }
                }
            }, alwaysShowLabel = false
            )
        }
    }
}