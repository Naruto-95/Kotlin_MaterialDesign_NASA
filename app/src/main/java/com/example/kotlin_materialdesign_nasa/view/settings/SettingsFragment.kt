package com.example.kotlin_materialdesign_nasa.view.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentSettingsBinding
import com.example.kotlin_materialdesign_nasa.utils.greenTheme
import com.example.kotlin_materialdesign_nasa.utils.mainTheme
import com.example.kotlin_materialdesign_nasa.utils.redTheme
import com.example.kotlin_materialdesign_nasa.view.MainActivity
import com.google.android.material.tabs.TabLayout


class SettingsFragment : Fragment(), View.OnClickListener {


    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


    private lateinit var parentActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity =
            requireActivity() as MainActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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

    private fun init() {
        binding.btnOne.setOnClickListener(this)
        binding.btnTwo.setOnClickListener(this)
        binding.btnThree.setOnClickListener(this)

        when (parentActivity.getStyleTheme()) {
            1 -> binding.radioGroup.check(R.id.btnOne)
            2 -> binding.radioGroup.check(R.id.btnTwo)
            3 -> binding.radioGroup.check(R.id.btnThree)

        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnOne -> {
                parentActivity.setStyleTheme(mainTheme)
                parentActivity.recreate()
            }
            R.id.btnTwo -> {
                parentActivity.setStyleTheme(redTheme)
                parentActivity.recreate()
            }
            R.id.btnThree -> {
                parentActivity.setStyleTheme(greenTheme)
                parentActivity.recreate()
            }

        }

    }


    companion object {

        @JvmStatic
        fun newInstance() = SettingsFragment()

    }
}