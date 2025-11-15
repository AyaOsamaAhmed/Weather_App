package com.aya.domain.repo

import com.aya.domain.entity.CityModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepo {

    suspend fun getAllCitiesDB(): Flow<List<CityModel>>
}