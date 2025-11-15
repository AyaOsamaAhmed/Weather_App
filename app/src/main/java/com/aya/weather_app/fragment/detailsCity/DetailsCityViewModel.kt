package com.aya.weather_app.fragment.detailsCity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aya.domain.entity.CityModel
import com.aya.domain.useCase.city.UpdateCityUseCase
import com.aya.domain.useCase.details.DetailsCityUseCase
import com.aya.domain.useCase.details.InsertDetailsCityDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CityState   {
    object Loading : CityState()
    data class Success(val data: CityModel) : CityState()
    data class Error(val message: String) : CityState()
}

@HiltViewModel
class DetailsCityViewModel @Inject constructor(

    private val detailsCityUseCase: DetailsCityUseCase,
    private val insertDetailsCityDBUseCase : InsertDetailsCityDBUseCase,
    private val updateCityUseCase: UpdateCityUseCase
): ViewModel() {


    private val _state = MutableStateFlow<CityState>(CityState.Loading)
    val state = _state.asStateFlow()



    fun loadCity(name:String) = viewModelScope.launch {

        _state.value = CityState.Loading

        try {

          val data =  detailsCityUseCase(name)

             _state.value = CityState.Success(data)
            insertDetailsCityToDB(data)
            updateCity(CityModel(
                cityId = data.cityId,
                name = data.name,
                country = data.country
            ))

            Log.d("DetailsCityViewModel", "loadCity: ${data}" )

        } catch (e: Exception) {
            _state.value = CityState.Error(e.message ?: "Unknown Error")
            Log.d("DetailsCityViewModel", "error: ${e.message}" )

        }
    }

    private fun insertDetailsCityToDB(city: CityModel) = viewModelScope.launch {
        insertDetailsCityDBUseCase(city)
    }

    private fun updateCity(city:CityModel) = viewModelScope.launch {
        updateCityUseCase(city)
    }


}