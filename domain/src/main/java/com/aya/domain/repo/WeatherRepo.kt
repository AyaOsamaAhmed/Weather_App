package com.aya.domain.repo

import com.aya.domain.entity.CityModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepo {

    suspend fun getAllCitiesDB(): Flow<List<CityModel>>

    suspend fun insertCityToDB(city: CityModel)

    suspend fun updateCityIdToDB(city: CityModel)

    suspend fun checkCityToDB(city: String) :Boolean

    //
    suspend fun getDetailsCityFromApi(city: String): CityModel

    suspend fun insertDetailsCityToDB(city: CityModel)

    //
    suspend fun getHistoricalWeatherDB(name :String): Flow<List<CityModel>>
}