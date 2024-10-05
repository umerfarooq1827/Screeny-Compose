package com.shahid.iqbal.screeny.utils

import android.os.Environment
import java.io.File

object FileUtils {

    private const val FOLDER = "Screeny"
    private val FILE_NAME: String = "screeny_wallpaper_${System.currentTimeMillis()}.png"
    private val DIRECTORY_NAME: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)


    fun getFilePath(): String {
        val directory = File(DIRECTORY_NAME, FOLDER)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val file = File(directory, FILE_NAME)
        return file.absolutePath
    }
}