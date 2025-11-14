package com.aya.weather_app.city

import android.app.Application
import com.aya.weather_app.base.Action
import com.aya.weather_app.base.AndroidBaseViewModel

sealed class CityAction  : Action {
    data class CitySuccess(val data :String ) : CityAction()
    data class ShowLoading(val show: Boolean) : CityAction()
}


class CityViewModel (
    app: Application,
): AndroidBaseViewModel<CityAction>(app) {


}