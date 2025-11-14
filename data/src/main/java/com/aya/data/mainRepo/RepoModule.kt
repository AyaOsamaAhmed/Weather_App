package com.aya.data.mainRepo

import com.aya.data.local.dao.CityDao
import com.aya.data.local.dao.WeatherDao
import com.aya.data.remote.ApiWeather
import com.aya.domain.repo.WeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideWeatherRepo(
        apiWeather: ApiWeather,
        cityDao: CityDao,
        weatherDao: WeatherDao
    ): WeatherRepo {
        return WeatherRepoImpl(
            apiWeather = apiWeather,
            cityDao = cityDao,
            weatherDao = weatherDao
        )
    }

}
