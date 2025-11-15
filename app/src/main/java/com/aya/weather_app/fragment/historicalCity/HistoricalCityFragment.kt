package com.aya.weather_app.fragment.historicalCity

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.aya.data.remote.Constant.Companion.ICON_URL
import com.aya.domain.entity.CityModel
import com.aya.weather_app.R
import com.aya.weather_app.base.BaseFragment
import com.aya.weather_app.databinding.FragmentDetailsBinding
import com.aya.weather_app.databinding.FragmentHistoricalBinding
import com.aya.weather_app.extension.hide
import com.aya.weather_app.extension.show
import com.aya.weather_app.extension.showMessage
import com.aya.weather_app.fragment.city.adapter.CitiesAdapter
import com.aya.weather_app.fragment.historicalCity.adapter.HistoricalCityAdapter
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HistoricalCityFragment : BaseFragment<FragmentHistoricalBinding, HistoricalCityViewModel>()  {

    private lateinit var nav : NavHostFragment
    private lateinit var navController : NavController
    override val mViewModel: HistoricalCityViewModel by viewModels()


    private val historicalCityAdapter: HistoricalCityAdapter by lazy { HistoricalCityAdapter() }
    val args: HistoricalCityFragmentArgs by navArgs()

    override fun onFragmentReady() {
        handleViewState()
        val cityName = args.city
        mViewModel.loadWeather(cityName)
        setupHistoricalCityRecyclerView()


        binding.apply{

            ivBack.setOnClickListener {
                navController.navigate(R.id.action_historicalCityFragment_to_cityFragment)
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
                            binding.tvTitle.text = " ${city.firstOrNull()?.name ?: ""} historical"
                            // Update UI with the list of cities
                             historicalCityAdapter.submitList(city)
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


    private fun setupHistoricalCityRecyclerView() {
        binding.rvHistoricalCity.layoutManager = LinearLayoutManager(context)
        binding.rvHistoricalCity.adapter = historicalCityAdapter
    }

}