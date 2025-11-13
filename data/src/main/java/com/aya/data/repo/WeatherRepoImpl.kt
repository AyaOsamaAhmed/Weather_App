package com.aya.data.repo

import com.aya.data.Constant.Companion.API_KEY
import com.aya.data.Constant.Companion.BASE_URL
import com.aya.data.entity.WeatherResponse
import com.aya.data.remote.ApiWeather
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WeatherRepoImpl( private val apiWeather: ApiWeather ,
    private val ktor: HttpClient) : WeatherRepo {
    override suspend fun getWeatherByNameFromRemote(cityName: String) : WeatherResponse {
        return apiWeather.getWeatherByName(cityName)

    }

    override suspend fun getWeatherByNameFromKtor(cityName: String) :  Result<WeatherResponse> {
        // Using Ktor client to fetch weather data
         return kotlin.runCatching {
             ktor.get(BASE_URL+"data/2.5/weather?q=$cityName&appid=$API_KEY").body()
         }
    }
}