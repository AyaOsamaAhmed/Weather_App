package com.aya.data.local.repo

import com.aya.data.local.dao.CityDao
import com.aya.data.local.dao.WeatherDao
import com.aya.data.local.entity.CityEntity
import com.aya.data.local.entity.Weather
import com.aya.domain.entity.CityModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepo @Inject constructor(
    private val cityDao: CityDao,
    private val weatherDao: WeatherDao
) {
    // City operations
    suspend fun insertCity(cityEntity: CityEntity) = cityDao.insert(cityEntity)
    suspend fun updateCity(cityEntity: CityEntity) = cityDao.updateCity(cityId = cityEntity.cityId?:"" , name = cityEntity.name , country = cityEntity.country ?:"")
    suspend fun getAllCities(): Flow<List<CityEntity>> = cityDao.getAllCities()
    suspend fun checkCity(cityName: String): Boolean = cityDao.checkCity(cityName)

    // Weather operations
    suspend fun insertWeather(weather: Weather) = weatherDao.insert(weather)
    suspend fun getWeatherHistory(name :String): Flow<List<Weather>> = weatherDao.getWeatherHistory(name)
}