package com.aya.domain.entity


data class CityModel(
    val id: String = "" ,
    val cityId: String = "" ,
    val name: String,
    val country: String = "",
    val description : String = "",
    val temperature: String = "" ,
    val icon : String = "" ,
    val humidity :String = "" ,
    val windSpeed :String = "",
    val date :String = "",
    val time :String = ""
)
