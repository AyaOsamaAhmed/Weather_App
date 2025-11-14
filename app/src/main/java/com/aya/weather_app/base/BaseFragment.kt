package com.aya.weather_app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : Fragment() {

    abstract fun onFragmentReady()

    protected abstract val mViewModel: VM

    private var _binding: B? = null
    lateinit var binding: B
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindView()
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentReady()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
