package com.novandi.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.novandi.core.data.source.local.entity.PrayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayDao {
    @Query("SELECT * FROM pray")
    fun getAllPray(): Flow<List<PrayEntity>>

    @Query("SELECT * FROM pray WHERE isFavorite = 1")
    fun getFavoritePray(): Flow<List<PrayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPray(pray: List<PrayEntity>)

    @Update
    fun updateFavoritePray(pray: PrayEntity)
}