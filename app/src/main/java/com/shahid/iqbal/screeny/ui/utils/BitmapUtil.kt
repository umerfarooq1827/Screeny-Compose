package com.shahid.iqbal.screeny.ui.utils

import android.graphics.Bitmap
import android.graphics.Color

object BitmapUtil {
    fun isImageLight(bitmap: Bitmap): Boolean {
        var totalLuminance = 0.0
        val width = bitmap.width
        val height = bitmap.height

        val totalPixels = width * height

        for (y in 0 until height) {
            for (x in 0 until width) {
                val pixelColor = bitmap.getPixel(x, y)
                val luminance =
                    (Color.red(pixelColor) * 0.299 + Color.green(pixelColor) * 0.587 + Color.blue(
                        pixelColor
                    ) * 0.114) / 255
                totalLuminance += luminance
            }
        }

        val averageLuminance = totalLuminance / totalPixels
        return averageLuminance > 0.7
    }

    fun createBitmapCopy(bitmap: Bitmap): Bitmap? {
        return runCatching {
            bitmap.copy(Bitmap.Config.ARGB_8888, true)
        }.getOrNull()
    }
}