package com.aya.weather_app.fragment.city

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aya.domain.entity.CityModel
import com.aya.domain.useCase.city.GetAllCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

sealed class CityState   {
    object Loading : CityState()
    object Empty : CityState()
    data class Success(val data: List<CityModel>) : CityState()
    data class Error(val message: String) : CityState()
}

@HiltViewModel
class CityViewModel @Inject constructor(

    private val getAllCitiesUseCase: GetAllCitiesUseCase
): ViewModel() {


    private val _state = MutableStateFlow<CityState>(CityState.Loading)
    val state = _state.asStateFlow()


    init {
        loadCities()
    }

    private fun loadCities() = viewModelScope.launch {

        _state.value = CityState.Loading

        try {

           getAllCitiesUseCase().collect { it ->
                if (it.size == 0)
                    _state.value = CityState.Empty
                else
                    _state.value = CityState.Success(it)

               Log.d("CityViewModel", "loadCities: ${it}" )
            }
        } catch (e: Exception) {
            _state.value = CityState.Error(e.message ?: "Unknown Error")
        }
    }
}