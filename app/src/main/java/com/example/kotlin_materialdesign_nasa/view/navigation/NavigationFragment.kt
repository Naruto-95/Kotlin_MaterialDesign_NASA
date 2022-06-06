package com.example.kotlin_materialdesign_nasa.view.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentMyBinding


class NavigationFragment : Fragment() {

    private var _binding: FragmentMyBinding? = null
    private val binding: FragmentMyBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.today -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.containerr, PhotoDayYesterdayFragment.newInstance()).commit()

                }
                R.id.earth -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.containerr, PhotoEarthAndMoonFragment.newInstance()).commit()

                }
                R.id.mars -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.containerr, PhotoMarsAndWeatherFragment.newInstance()).commit()

                }



            }
            true

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = NavigationFragment()
    }
}

