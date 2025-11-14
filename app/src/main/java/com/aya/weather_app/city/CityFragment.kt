package com.aya.weather_app.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aya.weather_app.R
import com.aya.weather_app.base.BaseFragment
import com.aya.weather_app.databinding.FragmentCityBinding



class CityFragment : BaseFragment<FragmentCityBinding, CityViewModel>() {

    private lateinit var nav : NavHostFragment
    private lateinit var navController : NavController
    override val mViewModel: CityViewModel by viewModels()

    override fun onFragmentReady() {
        mViewModel.apply {
           /* observe(viewState) {
                handleViewState(it)
            }*/
        }
        binding.apply {

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

    private fun handleViewState(action: CityAction) {
        when (action) {
            is CityAction.ShowLoading -> {

            }

            is CityAction.CitySuccess -> {
            }

        }
    }

}