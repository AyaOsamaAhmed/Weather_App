package com.aya.weather_app.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aya.weather_app.R
import com.aya.weather_app.base.BaseActivity
import com.aya.weather_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>()  {

    private lateinit var nav : NavHostFragment
    private lateinit var navController : NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nav =   supportFragmentManager.findFragmentById(R.id.main_layout) as NavHostFragment
        navController = nav.navController

    }


}