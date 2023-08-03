package com.novandi.core.utils

import com.novandi.core.data.source.local.entity.PrayEntity
import com.novandi.core.data.source.remote.response.PrayResponse
import com.novandi.core.domain.model.Pray


object DataMapper {
    fun mapResponsesToEntities(input: List<PrayResponse>): List<PrayEntity> {
        val prayList = ArrayList<PrayEntity>()
        input.map {
            val pray = PrayEntity(
                id = it.id,
                doa = it.doa,
                ayat = it.ayat,
                latin = it.latin,
                artinya = it.artinya
            )
            prayList.add(pray)
        }

        return prayList
    }

    fun mapEntitiesToDomain(input: List<PrayEntity>): List<Pray> =
        input.map {
            Pray(
                id = it.id,
                doa = it.doa,
                ayat = it.ayat,
                latin = it.latin,
                artinya = it.artinya,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Pray) = PrayEntity(
        id = input.id,
        doa = input.doa,
        ayat = input.ayat,
        latin = input.latin,
        artinya = input.artinya,
        isFavorite = input.isFavorite
    )
}