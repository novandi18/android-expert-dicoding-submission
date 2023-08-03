package com.novandi.core.di

import com.novandi.core.domain.usecase.PrayInteractor
import com.novandi.core.domain.usecase.PrayUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun providePrayUseCase(prayInteractor: PrayInteractor): PrayUseCase
}