package com.novandi.doaharianku.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.novandi.core.domain.model.Pray
import com.novandi.core.domain.usecase.PrayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val prayUseCase: PrayUseCase) : ViewModel() {
    private val _favorite = MutableLiveData(false)
    val favorite: LiveData<Boolean> get() = _favorite

    fun setFavoritePray(pray: Pray, status: Boolean) {
        prayUseCase.setFavoritePray(pray, status)
        _favorite.value = status
    }

    fun setFavorite(isFavorite: Boolean) {
        _favorite.value = isFavorite
    }
}