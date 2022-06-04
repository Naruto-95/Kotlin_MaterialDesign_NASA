package com.example.kotlin_materialdesign_nasa.view.navigation.space

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentMeteoritesAndAsteroidsBinding
import com.example.kotlin_materialdesign_nasa.databinding.FragmentNavigationBinding
import com.example.kotlin_materialdesign_nasa.databinding.FragmentPhotoDayYesterdayBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MeteoritesAndAsteroidsFragment : Fragment() {

    private var _binding: FragmentMeteoritesAndAsteroidsBinding? = null
    private val binding: FragmentMeteoritesAndAsteroidsBinding
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
        _binding = FragmentMeteoritesAndAsteroidsBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = MeteoritesAndAsteroidsFragment()
    }
}