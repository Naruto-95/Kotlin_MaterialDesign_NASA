package com.example.kotlin_materialdesign_nasa.view.navigation.space

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentNavigationBinding
import com.example.kotlin_materialdesign_nasa.databinding.FragmentPhotoDayYesterdayBinding
import com.example.kotlin_materialdesign_nasa.databinding.FragmentSpaceWeatherBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SpaceWeatherFragment : Fragment() {

    private var _binding: FragmentSpaceWeatherBinding? = null
    private val binding: FragmentSpaceWeatherBinding
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
        _binding = FragmentSpaceWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = SpaceWeatherFragment()
    }
}