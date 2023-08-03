package com.novandi.core.data.source.local

import com.novandi.core.data.source.local.entity.PrayEntity
import com.novandi.core.data.source.local.room.PrayDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val prayDao: PrayDao) {
    fun getAllPray(): Flow<List<PrayEntity>> = prayDao.getAllPray()

    fun getFavoritePray(): Flow<List<PrayEntity>> = prayDao.getFavoritePray()

    suspend fun insertPray(prayList: List<PrayEntity>) = prayDao.insertPray(prayList)

    fun setFavoriteTourism(pray: PrayEntity, newState: Boolean) {
        pray.isFavorite = newState
        prayDao.updateFavoritePray(pray)
    }
}