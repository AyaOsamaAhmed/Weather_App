package com.aya.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cityId: String,
    val name: String,
    val country: String,
    val temperature: String,
    val description: String,
    val icon: String,
    val humidity: String,
    val windSpeed: String,
    val date: String,
    val time: String
)
