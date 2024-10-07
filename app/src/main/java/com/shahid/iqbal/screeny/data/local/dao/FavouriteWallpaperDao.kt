package com.shahid.iqbal.screeny.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shahid.iqbal.screeny.models.FavouriteWallpaper
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteWallpaperDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavourite(favouriteWallpaper: FavouriteWallpaper)

    @Delete
    suspend fun removeFromFavourite(wallpaper: FavouriteWallpaper)

    @Query("SELECT * FROM favourite_wallpaper WHERE id=:id limit 1")
    suspend fun getFavouriteById(id:Int):FavouriteWallpaper?

    @Query("SELECT * FROM favourite_wallpaper ORDER BY timeStamp Desc")
    fun getAllFavourites(): Flow<List<FavouriteWallpaper>>
}