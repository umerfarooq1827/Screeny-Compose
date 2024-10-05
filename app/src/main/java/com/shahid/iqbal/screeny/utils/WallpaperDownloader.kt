package com.shahid.iqbal.screeny.utils

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import com.shahid.iqbal.screeny.R
import java.io.File


interface Downloader {
    fun downloadFile(url: String)
}

class WallpaperDownloader(private val context: Context) : Downloader {

    private val downloadManager by lazy { context.getSystemService(DownloadManager::class.java) }

    override fun downloadFile(url: String) {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/png")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(context.getString(R.string.downloading_wallpaper))
            .setDestinationInExternalPublicDir(getAppDirectory(), "${System.currentTimeMillis()}.png")
            .setAllowedOverMetered(true)

        downloadManager.enqueue(request)
    }


    private fun getAppDirectory(): String {
        val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Screeny")
        if (!directory.exists()) {
            directory.mkdir()
        }
        return directory.absolutePath

    }
}