package com.novandi.core.di

import android.content.Context
import androidx.room.Room
import com.novandi.core.data.source.local.room.PrayDao
import com.novandi.core.data.source.local.room.PrayDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PrayDatabase {
        val builder = Room.databaseBuilder(
            context,
            PrayDatabase::class.java, "Pray.db"
        )
        val passphrase: ByteArray = SQLiteDatabase.getBytes("doaharianku".toCharArray())
        val factory = SupportFactory(passphrase)
        return builder.fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun providePrayDao(database: PrayDatabase): PrayDao = database.prayDao()
}