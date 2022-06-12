package com.example.kotlin_materialdesign_nasa.view.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentChipsBinding
import com.example.kotlin_materialdesign_nasa.view.navigation.PhotoDayYesterdayFragment
import com.example.kotlin_materialdesign_nasa.view.navigation.PhotoEarthAndMoonFragment
import com.example.kotlin_materialdesign_nasa.view.navigation.PhotoMarsAndWeatherFragment

class ChipsFragment : Fragment() {


    private var _binding: FragmentChipsBinding? = null
    private val binding: FragmentChipsBinding
        get() {
            return _binding!!
        }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openFargments()


    }

    private fun openFargments() {

        binding.fab.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
                .replace(R.id.container, PhotoDayYesterdayFragment.newInstance()).addToBackStack("")
                .commit()
        }
        binding.fab1.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
                .replace(R.id.container, PhotoEarthAndMoonFragment.newInstance()).addToBackStack("")
                .commit()
        }
        binding.fab2.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
                .replace(R.id.container, PhotoMarsAndWeatherFragment.newInstance())
                .addToBackStack("")
                .commit()
        }


    }


    companion object {

        @JvmStatic
        fun newInstance() = ChipsFragment()

    }
}
