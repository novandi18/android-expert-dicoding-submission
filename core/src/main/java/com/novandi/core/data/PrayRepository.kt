package com.novandi.core.data

import com.novandi.core.data.source.local.LocalDataSource
import com.novandi.core.data.source.remote.RemoteDataSource
import com.novandi.core.data.source.remote.network.ApiResponse
import com.novandi.core.data.source.remote.response.PrayResponse
import com.novandi.core.domain.model.Pray
import com.novandi.core.domain.repository.PrayRepositoryImpl
import com.novandi.core.utils.AppExecutors
import com.novandi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PrayRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : PrayRepositoryImpl {
    override fun getAllPray(): Flow<Resource<List<Pray>>> =
        object : NetworkBoundResource<List<Pray>, List<PrayResponse>>() {
            override fun loadFromDB(): Flow<List<Pray>> {
                return localDataSource.getAllPray().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Pray>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<PrayResponse>>> =
                remoteDataSource.getAllPray()

            override suspend fun saveCallResult(data: List<PrayResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertPray(tourismList)
            }
        }.asFlow()

    override fun getFavoritePray(): Flow<List<Pray>> {
        return localDataSource.getFavoritePray().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoritePray(pray: Pray, state: Boolean) {
        val prayEntity = DataMapper.mapDomainToEntity(pray)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTourism(prayEntity, state)
        }
    }
}