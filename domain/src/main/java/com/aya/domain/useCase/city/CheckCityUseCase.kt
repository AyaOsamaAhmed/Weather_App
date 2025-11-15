package com.aya.domain.useCase.city

import com.aya.domain.repo.WeatherRepo
import javax.inject.Inject

class CheckCityUseCase @Inject constructor(
    private val repo: WeatherRepo
) {
    suspend operator fun invoke(name: String):Boolean = repo.checkCityToDB(name)
}