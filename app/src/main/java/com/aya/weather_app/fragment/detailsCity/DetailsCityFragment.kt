package com.aya.weather_app.fragment.detailsCity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.aya.data.remote.Constant.Companion.ICON_URL
import com.aya.domain.entity.CityModel
import com.aya.weather_app.R
import com.aya.weather_app.base.BaseFragment
import com.aya.weather_app.databinding.FragmentDetailsBinding
import com.aya.weather_app.extension.hide
import com.aya.weather_app.extension.show
import com.aya.weather_app.extension.showMessage
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsCityFragment : BaseFragment<FragmentDetailsBinding, DetailsCityViewModel>()  {

    private lateinit var nav : NavHostFragment
    private lateinit var navController : NavController
    override val mViewModel: DetailsCityViewModel by viewModels()
    val args: DetailsCityFragmentArgs by navArgs()

    override fun onFragmentReady() {
        handleViewState()
        val cityName = args.city
        mViewModel.loadCity(cityName)

        binding.apply{
            ivBack.setOnClickListener {
                navController.navigate(R.id.action_detailsCityFragment_to_cityFragment)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nav =  activity?.supportFragmentManager?.findFragmentById(R.id.main_layout) as NavHostFragment
        navController = nav.navController

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleViewState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                mViewModel.state.collect { state ->

                    when (state) {

                        CityState.Loading -> {
                            // Show loading indicator
                            binding.progressBar.show()
                        }


                        is CityState.Success -> {
                            binding.progressBar.hide()

                            val city = state.data
                            // Update UI with the list of cities
                            setDataUI(city)
                        }

                        is CityState.Error -> {
                            val errorMessage = state.message
                            // Show error message
                            binding.body.showMessage(errorMessage)
                        }
                    }
                }
            }
        }
    }

    fun setDataUI(city: CityModel) {
        binding.apply {
            tvCityName.text = "${city.name}, ${city.country}"
            tvDesc.text = city.description
            tvTemp.text = city.temperature
            tvHumi.text = city.humidity
            tvWind.text = city.windSpeed
            tvWeatherInfo.text =
                "Weather information for ${city.name} received on \n ${city.date} - ${city.time}"

            val iconUrl = ICON_URL + city.icon + ".png"

            Glide.with(binding.root)
                .load(iconUrl)
                .placeholder(R.mipmap.location_city_24px)
                .into(imgCityIcon)


        }
    }

}