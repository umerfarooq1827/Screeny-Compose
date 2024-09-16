package com.shahid.iqbal.screeny.core.utils

import android.util.Log

object Extensions {


    fun Any?.debug(tag: String = "PexelApi") {
        Log.d(tag, "$this")
    }
}