package com.aya.data.local.repo

import com.aya.data.local.dao.CityDao
import com.aya.data.local.dao.WeatherDao
import com.aya.data.local.entity.CityEntity
import com.aya.data.local.entity.Weather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepo @Inject constructor(
    private val cityDao: CityDao,
    private val weatherDao: WeatherDao
) {
    // City operations
    suspend fun insertCity(cityEntity: CityEntity) = cityDao.insert(cityEntity)
    suspend fun getAllCities(): Flow<List<CityEntity>> = cityDao.getAllCities()


    // Weather operations
    suspend fun insertWeather(weather: Weather) = weatherDao.insert(weather)
    suspend fun getWeatherHistory(cityId: String): Flow<List<Weather>> = weatherDao.getWeatherHistory(cityId)
}