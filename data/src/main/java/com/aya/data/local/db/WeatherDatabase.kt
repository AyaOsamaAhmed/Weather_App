package com.aya.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aya.data.local.dao.CityDao
import com.aya.data.local.dao.WeatherDao
import com.aya.data.local.entity.CityEntity
import com.aya.data.local.entity.Weather

@Database(
    entities = [CityEntity::class , Weather::class],
    version = 2,
    exportSchema = true
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun weatherDao(): WeatherDao


    companion object {
        const val DATABASE_NAME = "weather_db"

        val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL(
                        "ALTER TABLE CityEntity ADD COLUMN country TEXT NOT NULL DEFAULT ''"
                    )
                }
            }

    }
}
