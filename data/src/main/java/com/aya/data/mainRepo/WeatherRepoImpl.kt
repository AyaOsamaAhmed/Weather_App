package com.aya.data.mainRepo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.aya.data.local.entity.CityEntity
import com.aya.data.local.entity.Weather
import com.aya.data.local.repo.DatabaseRepo
import com.aya.data.remote.ApiWeather
import com.aya.domain.entity.CityModel
import com.aya.domain.repo.WeatherRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class WeatherRepoImpl  (
    private val apiWeather: ApiWeather,
    private val db: DatabaseRepo
): WeatherRepo {
        override suspend fun getAllCitiesDB(): Flow<List<CityModel>> {
               val data = db.getAllCities()

                return data.map { result ->
                        result.map {
                                CityModel(
                                       id= it.id.toString(),
                                        cityId = it.cityId?:"",
                                       name =  it.name,
                                       country =  it.country.toString()
                                )
                        }
                }

        }

    override suspend fun insertCityToDB(city: CityModel) {
        db.insertCity(CityEntity( name = city.name ))

    }

    override suspend fun updateCityIdToDB(city: CityModel) {
        db.updateCity(cityEntity = CityEntity(
            cityId = city.cityId,
            name = city.name,
            country = city.country
        ))
    }

    override suspend fun checkCityToDB(city: String) :Boolean {
        return db.checkCity(city)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getDetailsCityFromApi(city: String): CityModel {
        val response = apiWeather.getWeatherByName(city)
        Log.d("WeatherRepoImpl", "getDetailsCityFromApi: ${response}" )
        val formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formatterTime = DateTimeFormatter.ofPattern("HH:mm")
        return CityModel(
            id = response.id.toString(),
            name = response.name,
            country = response.sys.country,
            description = response.weather[0].main,
            temperature = response.main.temp.toString().plus("°C"),
            icon = response.weather[0].icon,
            humidity = response.main.humidity.toString().plus(" %"),
            windSpeed = response.wind.speed.toString().plus(" Km/h"),
            date = LocalDate.now().format(formatterDate).toString(),
            time = LocalTime.now().format(formatterTime).toString()

        )
    }

    override suspend fun insertDetailsCityToDB(city: CityModel) {
        db.insertWeather(
            Weather(
                cityId = city.id,
                name = city.name,
                country = city.country,
                description = city.description,
                temperature = city.temperature,
                icon = city.icon,
                humidity = city.humidity,
                windSpeed = city.windSpeed,
                date = city.date,
                time = city.time
            )
        )
    }

    override suspend fun getHistoricalWeatherDB(name :String): Flow<List<CityModel>> {
        val data = db.getWeatherHistory(name)
        return data.map { result ->
            result.map {
                CityModel(
                    id = it.cityId.toString(),
                    name = it.name,
                    country = it.country,
                    description = it.description,
                    temperature = it.temperature,
                    icon = it.icon,
                    humidity = it.humidity,
                    windSpeed = it.windSpeed,
                    date = it.date,
                    time = it.time
                )
            }
        }
    }


}