package com.aya.weather_app.fragment.city.bottomSheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aya.domain.entity.CityModel
import com.aya.domain.useCase.city.CheckCityUseCase
import com.aya.domain.useCase.city.InsertCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AddCityState   {
    object Loading : AddCityState()
    object Wait: AddCityState()
    object Empty : AddCityState()
    object Add : AddCityState()
    data class Error(val message: String) : AddCityState()
}

@HiltViewModel
class AddCityViewModel @Inject constructor(
    private val checkCityUseCase: CheckCityUseCase ,
    private val insertCityUseCase: InsertCityUseCase
): ViewModel() {


    private val _state = MutableStateFlow<AddCityState>(AddCityState.Wait)
    val state = _state.asStateFlow()


     fun checkCity(city:String)= viewModelScope.launch {
            if (city.isEmpty()){
                _state.value = AddCityState.Error("City name can't be empty")
            }else{
                val check = checkCityUseCase(city)
                if(check)
                    _state.value = AddCityState.Error("City already exists")
                else
                    _state.value = AddCityState.Add
            }
    }


    fun addCityToDB(city :String) = viewModelScope.launch {
        _state.value = AddCityState.Loading

        val cityModel = CityModel(name = city)
        insertCityUseCase(cityModel)
        _state.value = AddCityState.Empty
    }

}