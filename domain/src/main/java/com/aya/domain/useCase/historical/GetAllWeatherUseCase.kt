package com.aya.domain.useCase.historical

import com.aya.domain.entity.CityModel
import com.aya.domain.repo.WeatherRepo
import javax.inject.Inject

class GetAllWeatherUseCase @Inject constructor(
    private val repo: WeatherRepo
) {
    suspend operator fun invoke(name : String) = repo.getHistoricalWeatherDB(name)
}