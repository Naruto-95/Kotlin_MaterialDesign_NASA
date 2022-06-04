package com.example.kotlin_materialdesign_nasa.view.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentNavigationBinding
import com.example.kotlin_materialdesign_nasa.databinding.FragmentPhotoEarthAndMoonBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PhotoEarthAndMoonFragment : Fragment() {

    private var _binding: FragmentPhotoEarthAndMoonBinding? = null
    private val binding: FragmentPhotoEarthAndMoonBinding
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
        _binding = FragmentPhotoEarthAndMoonBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = PhotoEarthAndMoonFragment()
    }
}