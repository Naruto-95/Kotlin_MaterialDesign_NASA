package com.example.kotlin_materialdesign_nasa.view.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentNavigationBinding
import com.example.kotlin_materialdesign_nasa.view.navigation.MyFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class NavigationFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentNavigationBinding? = null
    private val binding: FragmentNavigationBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickDialog()
    }


    private fun clickDialog() {
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_one -> {

                    Toast.makeText(context, "1", Toast.LENGTH_LONG).show()
                    if (dialog?.isShowing == true) {
                        dialog!!.dismiss()
                    }
                }

                R.id.navigation_two -> {
                    Toast.makeText(context, "2", Toast.LENGTH_LONG).show()
                    if (dialog?.isShowing == true) {
                        dialog!!.dismiss()
                    }
                }
                R.id.navigation_photo -> {
                    Toast.makeText(context, "3", Toast.LENGTH_LONG).show()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MyFragment.newInstance()).addToBackStack("")
                        .commit()
                    if (dialog?.isShowing == true) {
                        dialog!!.dismiss()
                    }
                }
            }
            true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = NavigationFragment()
    }
}