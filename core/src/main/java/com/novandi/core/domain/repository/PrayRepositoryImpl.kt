package com.novandi.core.domain.repository

import com.novandi.core.data.Resource
import com.novandi.core.domain.model.Pray
import kotlinx.coroutines.flow.Flow

interface PrayRepositoryImpl {
    fun getAllPray(): Flow<Resource<List<Pray>>>
    fun getFavoritePray(): Flow<List<Pray>>
    fun setFavoritePray(pray: Pray, state: Boolean)
}