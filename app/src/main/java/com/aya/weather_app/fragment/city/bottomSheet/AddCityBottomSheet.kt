package com.aya.weather_app.fragment.city.bottomSheet

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aya.weather_app.R
import com.aya.weather_app.databinding.BottomSheetAddCityBinding
import com.aya.weather_app.extension.show
import com.aya.weather_app.extension.showMessage
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AddCityBottomSheet: BottomSheetDialogFragment() {


    private lateinit var binding: BottomSheetAddCityBinding


    private lateinit var nav : NavHostFragment
    private lateinit var navController : NavController
    private val mViewModel: AddCityViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.bottom_sheet_add_city, container, false)
        binding = BottomSheetAddCityBinding.bind(rootView)
        nav =  activity?.supportFragmentManager?.findFragmentById(R.id.main_layout) as NavHostFragment
        navController = nav.navController

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkExistingCity()
        handleViewState()

        binding.ivSearchIcon.setOnClickListener {
            checkCity()
        }
        binding.btnAddCity.setOnClickListener {
           mViewModel.addCityToDB(binding.etAddNewCity.text.toString())
        }

    }

    private fun checkExistingCity() {
        binding.etAddNewCity.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_DOWN) {
                checkCity()
            }

            true
        }
    }
    private fun checkCity() {
        mViewModel.checkCity(binding.etAddNewCity.text.toString())
    }

    private fun handleViewState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                mViewModel.state.collect { state ->

                    when (state) {

                        AddCityState.Wait -> {
                            // Show loading indicator
                            hideAllItem()
                        }

                        AddCityState.Loading -> {
                            // Show loading indicator
                            hideAllItem()
                            binding.progressBar.show()
                        }
                        AddCityState.Add -> {
                            hideAllItem()
                            binding.btnAddCity.show()
                        }

                        AddCityState.Empty -> {
                            // City added successfully,
                            navController.navigate(R.id.action_addCityBottomSheet_to_cityFragment)
                        }


                        is AddCityState.Error -> {
                            hideAllItem()
                            val errorMessage = state.message
                            binding.tvExistingCity.showMessage(errorMessage)
                        }
                    }
                }
            }
        }
    }


    private fun hideAllItem(){
        binding.tvExistingCity.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.btnAddCity.visibility = View.GONE
    }

}