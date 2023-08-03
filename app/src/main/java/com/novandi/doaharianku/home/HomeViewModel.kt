package com.novandi.doaharianku.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.novandi.core.domain.usecase.PrayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(prayUseCase: PrayUseCase) : ViewModel() {
    val pray = prayUseCase.getAllPray().asLiveData()
}