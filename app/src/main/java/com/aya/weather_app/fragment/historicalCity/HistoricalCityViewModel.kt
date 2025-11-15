package com.aya.weather_app.fragment.historicalCity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aya.domain.entity.CityModel
import com.aya.domain.useCase.city.GetAllCitiesUseCase
import com.aya.domain.useCase.city.InsertCityUseCase
import com.aya.domain.useCase.details.DetailsCityUseCase
import com.aya.domain.useCase.details.InsertDetailsCityDBUseCase
import com.aya.domain.useCase.historical.GetAllWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

sealed class CityState   {
    object Loading : CityState()
    data class Success(val data: List<CityModel>) : CityState()
    data class Error(val message: String) : CityState()
}

@HiltViewModel
class HistoricalCityViewModel @Inject constructor(

    private val getAllWeatherUseCase: GetAllWeatherUseCase,
): ViewModel() {


    private val _state = MutableStateFlow<CityState>(CityState.Loading)
    val state = _state.asStateFlow()



    fun loadWeather(name:String) = viewModelScope.launch {

        _state.value = CityState.Loading

        try {

         getAllWeatherUseCase(name).collect {
              _state.value = CityState.Success(it)

              Log.d("HistoricalCityViewModel", "loadCity: ${it}")
          }
        } catch (e: Exception) {
            _state.value = CityState.Error(e.message ?: "Unknown Error")
            Log.d("HistoricalCityViewModel", "error: ${e.message}" )

        }
    }





}