package com.example.kotlin_materialdesign_nasa.view.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.databinding.FragmentSettingsBinding
import com.google.android.material.tabs.TabLayout


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("111", "${tab?.position}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
              //  TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
              //  TODO("Not yet implemented")
            }


        })

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}