package com.aya.domain.useCase.details

import com.aya.domain.entity.CityModel
import com.aya.domain.repo.WeatherRepo
import javax.inject.Inject

class InsertDetailsCityDBUseCase @Inject constructor(
    private val repo: WeatherRepo
) {
    suspend operator fun invoke(city:CityModel) = repo.insertDetailsCityToDB(city)
}