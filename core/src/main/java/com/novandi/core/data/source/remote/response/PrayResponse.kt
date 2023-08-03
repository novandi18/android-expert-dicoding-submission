package com.novandi.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PrayResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("doa")
    val doa: String,

    @field:SerializedName("ayat")
    val ayat: String,

    @field:SerializedName("latin")
    val latin: String,

    @field:SerializedName("artinya")
    val artinya: String
)