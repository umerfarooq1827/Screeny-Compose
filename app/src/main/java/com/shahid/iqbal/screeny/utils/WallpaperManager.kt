package com.shahid.iqbal.screeny.utils

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.graphics.drawable.toBitmap
import com.shahid.iqbal.screeny.R
import com.shahid.iqbal.screeny.utils.Extensions.debug
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Enum class representing types of wallpaper settings:
 * - SET_AS_HOME_SCREEN: Sets the wallpaper for the home screen only.
 * - SET_AS_LOCK_SCREEN: Sets the wallpaper for the lock screen only.
 * - SET_AS_BOTH: Sets the wallpaper for both the home and lock screens.
 */
enum class WallpaperType {
    SET_AS_HOME_SCREEN, SET_AS_LOCK_SCREEN, SET_AS_BOTH
}

/**
 * A utility class for managing and setting wallpapers on the device.
 *
 * @property context The application context used to access system services and resources.
 */
class WallpaperManager(private val context: Context) {

    // Lazy-initialized instance of Android's WallpaperManager
    private val wallpaperManager by lazy {
        context.getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager
    }

    /**
     * Sets the specified drawable as wallpaper based on the provided WallpaperType.
     *
     * @param drawable The Drawable object to be converted to a Bitmap and set as wallpaper.
     * @param type The WallpaperType that defines where the wallpaper should be set
     *             (home screen, lock screen, or both).
     */
    fun setWallpaper(drawable: Drawable, type: WallpaperType) {
        // Launching a coroutine on the IO dispatcher for background processing
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Determine the appropriate flag based on the wallpaper type
                val flag = when (type) {
                    WallpaperType.SET_AS_HOME_SCREEN -> WallpaperManager.FLAG_SYSTEM
                    WallpaperType.SET_AS_LOCK_SCREEN -> WallpaperManager.FLAG_LOCK
                    WallpaperType.SET_AS_BOTH -> WallpaperManager.FLAG_LOCK or WallpaperManager.FLAG_SYSTEM
                }

                // Convert the drawable to a bitmap and set it as wallpaper with the chosen flag
                wallpaperManager.setBitmap(drawable.toBitmap(), null, true, flag)

                // Display a success message on the main thread
                showMessage(R.string.wallpaper_set_successfully)
            } catch (e: Exception) {
                // Handle any generic exceptions and display an error message
                showMessage(R.string.something_went_wrong)
            } catch (e: java.io.IOException) {
                // Handle IO exceptions separately and display an error message
                showMessage(R.string.something_went_wrong)
            }
        }
    }

    /**
     * Displays a toast message on the main thread.
     *
     * @param message The resource ID of the message to display.
     */
    private suspend inline fun showMessage(@StringRes message: Int) = withContext(Dispatchers.Main) {
        Toast.makeText(context, context.getString(message), Toast.LENGTH_SHORT).show()
    }
}
