package com.aya.weather_app.fragment.city

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
import com.aya.weather_app.R
import com.aya.weather_app.base.BaseFragment
import com.aya.weather_app.databinding.FragmentCityBinding
import com.aya.weather_app.extension.show
import com.aya.weather_app.extension.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CityFragment : BaseFragment<FragmentCityBinding, CityViewModel>() {

    private lateinit var nav : NavHostFragment
    private lateinit var navController : NavController
    override val mViewModel: CityViewModel by viewModels()

    override fun onFragmentReady() {
        handleViewState()
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
                            hideAllItem()
                            binding.progressBar.show()
                        }

                        CityState.Empty -> {
                            // Show empty state message
                            hideAllItem()
                            binding.tvNoCities.show()
                        }

                        is CityState.Success -> {
                            val cities = state.data
                            // Update UI with the list of cities
                            hideAllItem()
                            binding.rvCity.show()
                        }

                        is CityState.Error -> {
                            val errorMessage = state.message
                            binding.rvCity.showMessage(errorMessage)
                        }
                    }
                }
            }
        }
    }


    private fun hideAllItem(){
        binding.rvCity.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.tvNoCities.visibility = View.GONE
    }

}