package com.aya.data.mainRepo

import com.aya.data.local.repo.DatabaseRepo
import com.aya.data.remote.ApiWeather
import com.aya.domain.entity.CityModel
import com.aya.domain.repo.WeatherRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeatherRepoImpl  (
    private val apiWeather: ApiWeather,
    private val db: DatabaseRepo
): WeatherRepo {
        override suspend fun getAllCitiesDB(): Flow<List<CityModel>> {
               val data = db.getAllCities()

                return data.map { result ->
                        result.map {
                                CityModel(
                                        it.id,
                                        it.name,
                                        it.country
                                )
                        }
                }

        }


}