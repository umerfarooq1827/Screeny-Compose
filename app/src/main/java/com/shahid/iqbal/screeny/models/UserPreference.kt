package com.shahid.iqbal.screeny.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity("user_preference")
data class UserPreference(

    @PrimaryKey(autoGenerate = false)
    var id: Int = 1,

    var languageCode: String = "en",

    var systemMode: Int = -1,
    var isDynamicColor: Boolean = true
)
