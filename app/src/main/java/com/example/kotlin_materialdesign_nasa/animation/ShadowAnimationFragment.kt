package com.example.kotlin_materialdesign_nasa.animation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.databinding.FragmentShadowAnimationBinding

class ShadowAnimationFragment : Fragment() {


    private var _binding: FragmentShadowAnimationBinding? = null
    private val binding: FragmentShadowAnimationBinding
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
        _binding = FragmentShadowAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
binding.scrollView.setOnScrollChangeListener  { _, _, _, _, _ ->
    binding.header.isSelected = binding.scrollView.canScrollVertically(-1)
}


    }







    companion object {

        @JvmStatic
        fun newInstance() = ShadowAnimationFragment()

    }
}