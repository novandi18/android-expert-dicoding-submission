package com.novandi.core.domain.usecase

import com.novandi.core.data.Resource
import com.novandi.core.domain.model.Pray
import com.novandi.core.domain.repository.PrayRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrayInteractor @Inject constructor(
    private val prayRepository: PrayRepositoryImpl
) : PrayUseCase {
    override fun getAllPray(): Flow<Resource<List<Pray>>> = prayRepository.getAllPray()

    override fun getFavoritePray(): Flow<List<Pray>> = prayRepository.getFavoritePray()

    override fun setFavoritePray(pray: Pray, state: Boolean) = prayRepository.setFavoritePray(pray, state)
}