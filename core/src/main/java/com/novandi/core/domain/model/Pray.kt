package com.novandi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pray(
    val id: String,
    val doa: String,
    val ayat: String,
    val latin: String,
    val artinya: String,
    val isFavorite: Boolean
) : Parcelable