package com.aya.domain.useCase.city

import com.aya.domain.entity.CityModel
import com.aya.domain.repo.WeatherRepo
import javax.inject.Inject

class InsertCityUseCase @Inject constructor(
    private val repo: WeatherRepo
) {
    suspend operator fun invoke(cityModel: CityModel) = repo.insertCityToDB(cityModel)
}