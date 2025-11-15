package com.aya.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.aya.data.base.BaseDao
import com.aya.data.local.entity.Weather
import kotlinx.coroutines.flow.Flow


@Dao
abstract class WeatherDao :BaseDao<Weather> {
    @Query("SELECT * FROM weather WHERE name = :name")
    abstract fun getWeatherHistory(name: String): Flow<List<Weather>>
}