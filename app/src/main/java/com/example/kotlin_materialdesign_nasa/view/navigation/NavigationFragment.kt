package com.example.kotlin_materialdesign_nasa.view.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentMyBinding
import com.example.kotlin_materialdesign_nasa.view.navigation.viewpager.ViewPagerAdapter


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
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)

        binding.bottomNav.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.today -> {
                    requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                        .replace(R.id.containerr, PhotoDayYesterdayFragment.newInstance()).commit()

                }
                R.id.earth -> {
                    requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                        .replace(R.id.containerr, PhotoEarthAndMoonFragment.newInstance()).commit()

                }
                R.id.mars -> {
                    requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                        .replace(R.id.containerr, PhotoMarsAndWeatherFragment.newInstance())
                        .commit()

                }
            }
            true
        }
        binding.bottomNav.selectedItemId = R.id.earth
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

