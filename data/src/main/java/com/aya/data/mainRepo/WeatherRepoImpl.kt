package com.aya.data.mainRepo

import com.aya.data.local.dao.CityDao
import com.aya.data.local.dao.WeatherDao
import com.aya.data.remote.ApiWeather
import com.aya.domain.repo.WeatherRepo

class WeatherRepoImpl (
        apiWeather: ApiWeather,
        cityDao: CityDao,
        weatherDao: WeatherDao
): WeatherRepo {


}