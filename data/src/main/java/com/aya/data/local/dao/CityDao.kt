package com.aya.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.aya.data.base.BaseDao
import com.aya.data.local.entity.CityEntity
import kotlinx.coroutines.flow.Flow


@Dao
abstract class CityDao : BaseDao<CityEntity> {
    @Query("SELECT * FROM cityentity")
    abstract fun getAllCities(): Flow<List<CityEntity>>
}