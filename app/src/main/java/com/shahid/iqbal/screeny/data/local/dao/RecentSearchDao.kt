package com.shahid.iqbal.screeny.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.shahid.iqbal.screeny.models.RecentSearch
import kotlinx.coroutines.flow.Flow


@Dao
interface RecentSearchDao {

    @Upsert
    suspend fun saveRecent(recentSearch: RecentSearch)

    @Delete
    suspend fun removeRecent(recentSearch: RecentSearch)

    @Query("DELETE FROM recent_search")
    suspend fun clearAllRecent()

    @Query("SELECT * FROM recent_search order by date desc")
     fun getRecentSearches(): Flow<List<RecentSearch>>
}