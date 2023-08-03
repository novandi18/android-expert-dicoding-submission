package com.novandi.core.di

import com.novandi.core.data.PrayRepository
import com.novandi.core.domain.repository.PrayRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(prayRepository: PrayRepository): PrayRepositoryImpl
}