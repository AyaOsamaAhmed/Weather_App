package com.aya.weather_app.fragment.city.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aya.domain.entity.CityModel
import com.aya.weather_app.databinding.ItemCityBinding
import com.aya.weather_app.extension.layoutInflater

class CitiesAdapter(private val cityActionsListener: CityActionsListener) :
    ListAdapter<CityModel, CitiesAdapter.CityViewHolder>(callback) {

    companion object {
        private val callback = object : DiffUtil.ItemCallback<CityModel>() {
            override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = ItemCityBinding.inflate(parent.layoutInflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CityViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(city: CityModel) {
            binding.cityName.text = "${city.name}, ${city.country}"
            binding.cityItem.setOnClickListener { cityActionsListener.onCityClick(city) }
            binding.history.setOnClickListener {
                cityActionsListener.onCityWeatherHistoryClick(
                    city
                )
            }
        }
    }
}

interface CityActionsListener {
    fun onCityClick(city: CityModel)
    fun onCityWeatherHistoryClick(city: CityModel)
}