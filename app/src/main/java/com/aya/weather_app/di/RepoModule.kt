package com.aya.weather_app.di

import com.aya.data.local.repo.DatabaseRepo
import com.aya.data.mainRepo.WeatherRepoImpl
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
    fun provideWeatherRepository(api: ApiWeather, db : DatabaseRepo ): WeatherRepo {
        return WeatherRepoImpl(api,db)
    }

}
