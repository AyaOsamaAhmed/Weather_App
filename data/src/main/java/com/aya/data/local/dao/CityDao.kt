package com.aya.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.aya.data.base.BaseDao
import com.aya.data.local.entity.City
import kotlinx.coroutines.flow.Flow


@Dao
abstract class CityDao : BaseDao<City> {
    @Query("SELECT * FROM city")
    abstract fun getAllCities(): Flow<List<City>>
}