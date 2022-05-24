package com.example.kotlin_materialdesign_nasa.view.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.databinding.FragmentPictureBinding


class PictureFragment : Fragment() {
    private var _binding: FragmentPictureBinding? = null
    private val binding: FragmentPictureBinding
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
    ): View? {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = PictureFragment

    }
}