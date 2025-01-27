package com.shahid.iqbal.screeny.models

import android.content.res.Resources.Theme
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shahid.iqbal.screeny.ui.screens.settings.language.utils.AppMode

@Keep
@Entity("user_preference")
data class UserPreference(

    @PrimaryKey(autoGenerate = false)
    var id: Int = 1,

    var languageCode: String = "en",

    var appMode: AppMode = AppMode.DEFAULT,
    var shouldShowDynamicColor: Boolean = true
)
