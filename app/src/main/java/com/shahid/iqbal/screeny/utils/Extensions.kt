package com.shahid.iqbal.screeny.utils

import android.util.Log

object Extensions {


    fun Any?.debug(tag: String = "Logger") {
        Log.d(tag, "$this")
    }
}