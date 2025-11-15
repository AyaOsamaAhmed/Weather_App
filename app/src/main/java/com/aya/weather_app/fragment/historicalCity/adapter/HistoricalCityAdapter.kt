package com.aya.weather_app.fragment.historicalCity.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aya.data.remote.Constant.Companion.ICON_URL
import com.aya.domain.entity.CityModel
import com.aya.weather_app.R
import com.aya.weather_app.databinding.ItemCityBinding
import com.aya.weather_app.databinding.ItemHistoricalCityBinding
import com.aya.weather_app.extension.layoutInflater
import com.bumptech.glide.Glide

class HistoricalCityAdapter() :
    ListAdapter<CityModel, HistoricalCityAdapter.HistoricalCityViewHolder>(callback) {

    companion object {
        private val callback = object : DiffUtil.ItemCallback<CityModel>() {
            override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalCityViewHolder {
        val binding = ItemHistoricalCityBinding.inflate(parent.layoutInflater, parent, false)
        return HistoricalCityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoricalCityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HistoricalCityViewHolder(private val binding: ItemHistoricalCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(city: CityModel) {
            binding.apply {
                description.text = "${city.description}, ${city.temperature}"
                date.text = "${city.date} - ${city.time}"

                val iconUrl = ICON_URL + city.icon + ".png"

                Glide.with(binding.root)
                    .load(iconUrl)
                    .placeholder(R.mipmap.location_city_24px)
                    .into(icon)


            }

        }
    }
}

