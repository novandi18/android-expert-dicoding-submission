package com.novandi.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.novandi.core.data.source.local.entity.PrayEntity

@Database(entities = [PrayEntity::class], version = 1, exportSchema = false)
abstract class PrayDatabase : RoomDatabase() {
    abstract fun prayDao(): PrayDao
}