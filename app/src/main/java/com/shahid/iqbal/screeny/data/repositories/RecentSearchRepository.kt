package com.shahid.iqbal.screeny.data.repositories

import com.shahid.iqbal.screeny.data.local.dao.RecentSearchDao
import com.shahid.iqbal.screeny.models.RecentSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class RecentSearchRepository(private val dao: RecentSearchDao) {
    val recentSearches = dao.getRecentSearches()


    suspend fun saveRecent(recentSearch: RecentSearch) = withContext(Dispatchers.IO) {

        val searchList = dao.getRecentSearches().firstOrNull()

        if (searchList != null && searchList.size >= 20){
            dao.removeRecent(searchList.last())
            dao.saveRecent(recentSearch)
        }else {
            dao.saveRecent(recentSearch)
        }
    }

    suspend fun removeRecent(recentSearch: RecentSearch) = withContext(Dispatchers.IO) {
        dao.removeRecent(recentSearch)
    }

}