package com.example.kotlin_materialdesign_nasa.view.navigation.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.kotlin_materialdesign_nasa.utils.mainTheme
import com.example.kotlin_materialdesign_nasa.view.navigation.PhotoDayYesterdayFragment
import com.example.kotlin_materialdesign_nasa.view.navigation.PhotoEarthAndMoonFragment
import com.example.kotlin_materialdesign_nasa.view.navigation.PhotoMarsAndWeatherFragment

const val PhotoDay = 0
const val PhotoEarth = 1
const val PhotoMars = 2


class ViewPagerAdapter(private val fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val fragments = arrayOf(
        PhotoDayYesterdayFragment(),
        PhotoEarthAndMoonFragment(),
        PhotoMarsAndWeatherFragment()
    )

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            PhotoDay -> PhotoDayYesterdayFragment()
            PhotoEarth -> PhotoEarthAndMoonFragment()
            PhotoMars -> PhotoMarsAndWeatherFragment()

            else -> PhotoDayYesterdayFragment()
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            PhotoDay -> "PhotoDay"
            PhotoEarth -> "PhotoEarth"
            else -> "WeatherMars"
        }
    }


}