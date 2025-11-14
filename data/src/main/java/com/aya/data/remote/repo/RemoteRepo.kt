package com.aya.data.remote.repo

import com.aya.data.remote.response.WeatherResponse

interface RemoteRepo {

    suspend fun getWeatherByNameFromRemote(cityName : String) : WeatherResponse

    suspend fun getWeatherByNameFromKtor(cityName : String) : Result<WeatherResponse>



}