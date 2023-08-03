package com.novandi.core.data.source.remote.network

import com.novandi.core.data.source.remote.response.PrayResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api")
    suspend fun getAllPray(): List<PrayResponse>
}