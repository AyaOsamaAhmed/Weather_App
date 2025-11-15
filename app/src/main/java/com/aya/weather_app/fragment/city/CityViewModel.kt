package com.aya.weather_app.fragment.city

import androidx.lifecycle.ViewModel
import com.aya.weather_app.base.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class CityAction  : Action {
    data class CitySuccess(val data :String ) : CityAction()
    data class ShowLoading(val show: Boolean) : CityAction()
}

@HiltViewModel
class CityViewModel @Inject constructor(


): ViewModel() {


}