package com.example.kotlin_materialdesign_nasa.view.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.databinding.FragmentPhotoMarsAndWeatherBinding


class PhotoMarsAndWeatherFragment : Fragment() {

    private var _binding: FragmentPhotoMarsAndWeatherBinding? = null
    private val binding: FragmentPhotoMarsAndWeatherBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoMarsAndWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = PhotoMarsAndWeatherFragment()
    }
}