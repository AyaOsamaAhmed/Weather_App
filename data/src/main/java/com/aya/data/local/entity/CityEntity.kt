package com.aya.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var cityId: String? = null,
    var name: String,
    var country: String? = null
)
