package com.example.kotlin_materialdesign_nasa.view.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentChipsBinding

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

       binding.back.setOnClickListener {
           requireActivity().supportFragmentManager.popBackStack()
       }
    }




    companion object {

        @JvmStatic
        fun newInstance() = ChipsFragment()

    }
}