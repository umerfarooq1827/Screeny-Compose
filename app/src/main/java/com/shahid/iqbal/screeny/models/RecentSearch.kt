package com.shahid.iqbal.screeny.models

import androidx.compose.runtime.Stable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_search")
@Stable
data class RecentSearch(
    @PrimaryKey(autoGenerate = false)
    val query: String,
    val date: Long = System.currentTimeMillis(),
)
