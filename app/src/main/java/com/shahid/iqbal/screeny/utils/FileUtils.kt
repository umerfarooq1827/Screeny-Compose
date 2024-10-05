package com.shahid.iqbal.screeny.utils

import android.os.Environment

object FileUtils {

    const val FOLDER = "Screeny"
    val FILE_NAME = "screeny_wallpaper_${System.currentTimeMillis()}.png"
    val DIRECTORY_NAME = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)


    fun getFilePath(): String = "$DIRECTORY_NAME/$FOLDER/$FILE_NAME"
}