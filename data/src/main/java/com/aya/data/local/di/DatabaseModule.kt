package com.aya.data.local.di

import android.content.Context
import androidx.room.Room
import com.aya.data.local.dao.CityDao
import com.aya.data.local.dao.WeatherDao
import com.aya.data.local.db.WeatherDatabase
import com.aya.data.local.db.WeatherDatabase.Companion.MIGRATION_1_2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            WeatherDatabase.DATABASE_NAME
        )
          //  .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCityDao(db: WeatherDatabase): CityDao = db.cityDao()

    @Provides
    @Singleton
    fun provideWeatherDao(db: WeatherDatabase): WeatherDao = db.weatherDao()
}
