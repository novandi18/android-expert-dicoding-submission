package com.novandi.core.domain.usecase

import com.novandi.core.data.Resource
import com.novandi.core.domain.model.Pray
import kotlinx.coroutines.flow.Flow

interface PrayUseCase {
    fun getAllPray(): Flow<Resource<List<Pray>>>
    fun getFavoritePray(): Flow<List<Pray>>
    fun setFavoritePray(pray: Pray, state: Boolean)
}