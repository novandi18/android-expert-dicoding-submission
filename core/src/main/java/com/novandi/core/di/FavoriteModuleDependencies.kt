package com.novandi.core.di

import com.novandi.core.domain.usecase.PrayUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun prayUseCase(): PrayUseCase
}