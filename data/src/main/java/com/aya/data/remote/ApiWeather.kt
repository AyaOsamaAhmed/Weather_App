package com.aya.data.remote

import com.aya.data.remote.Constant.Companion.API_KEY
import com.aya.data.remote.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeather {

    @GET("data/2.5/weather")
    suspend fun getWeatherByName(@Query("q") cityName: String? = null,
                                 @Query("appid") appKey: String = API_KEY ): WeatherResponse


}