package com.aya.data.repo

import com.aya.data.entity.WeatherResponse

interface WeatherRepo {

    suspend fun getWeatherByNameFromRemote(cityName : String) : WeatherResponse

    suspend fun getWeatherByNameFromKtor(cityName : String) : Result<WeatherResponse>

}