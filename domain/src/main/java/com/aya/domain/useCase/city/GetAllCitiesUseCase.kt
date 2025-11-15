package com.aya.domain.useCase.city

import com.aya.domain.repo.WeatherRepo
import javax.inject.Inject

class GetAllCitiesUseCase @Inject constructor(
    private val repo: WeatherRepo
) {
    suspend operator fun invoke() = repo.getAllCitiesDB()
}