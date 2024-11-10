package com.shahid.iqbal.screeny.utils

import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.graphics.drawable.toBitmap
import com.shahid.iqbal.screeny.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


enum class WallpaperType {
    SET_AS_HOME_SCREEN, SET_AS_LOCK_SCREEN, SET_AS_BOTH
}

class WallpaperManager(private val context: Context) {

    private val wallpaperManager by lazy {
        context.getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager
    }


    fun setWallpaper(drawable: Drawable, type: WallpaperType) {
        CoroutineScope(Dispatchers.Default).launch {
            try {

                val flag = when (type) {
                    WallpaperType.SET_AS_HOME_SCREEN -> WallpaperManager.FLAG_SYSTEM
                    WallpaperType.SET_AS_LOCK_SCREEN -> WallpaperManager.FLAG_LOCK
                    WallpaperType.SET_AS_BOTH -> WallpaperManager.FLAG_LOCK or WallpaperManager.FLAG_SYSTEM
                }

                wallpaperManager.setBitmap(drawable.toBitmap(), null, false, flag)
                showMessage(R.string.wallpaper_set_successfully)
            } catch (e: Exception) {
                showMessage(R.string.something_went_wrong)
            } catch (e: java.io.IOException) {
                showMessage(R.string.something_went_wrong)
            }
        }
    }

    private suspend inline fun showMessage(@StringRes message: Int) = withContext(Dispatchers.Main) {
        Toast.makeText(context, context.getString(message), Toast.LENGTH_SHORT).show()
    }

}