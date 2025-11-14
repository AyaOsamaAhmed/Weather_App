package com.aya.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aya.data.local.dao.CityDao
import com.aya.data.local.dao.WeatherDao
import com.aya.data.local.entity.City
import com.aya.data.local.entity.Weather

@Database(
    entities = [City::class , Weather::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun weatherDao(): WeatherDao


    companion object {
        const val DATABASE_NAME = "weather_db"
    }
}
