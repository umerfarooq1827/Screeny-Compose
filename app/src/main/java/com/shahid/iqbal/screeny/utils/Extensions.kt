package com.shahid.iqbal.screeny.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

object Extensions {


    fun Any?.debug(tag: String = "Logger") {
        Log.d(tag, "$this")
    }

    @ReadOnlyComposable
    @Composable
    internal fun Dp.toPx(): Float {
        return this.value * LocalDensity.current.density
    }
}