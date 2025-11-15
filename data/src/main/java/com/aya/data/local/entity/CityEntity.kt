package com.aya.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val country: String
)
