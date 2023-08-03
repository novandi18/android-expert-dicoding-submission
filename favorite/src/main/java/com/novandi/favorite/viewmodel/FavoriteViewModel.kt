package com.novandi.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.novandi.core.domain.usecase.PrayUseCase

class FavoriteViewModel(prayUseCase: PrayUseCase) : ViewModel() {
    val favoritePray = prayUseCase.getFavoritePray().asLiveData()
}