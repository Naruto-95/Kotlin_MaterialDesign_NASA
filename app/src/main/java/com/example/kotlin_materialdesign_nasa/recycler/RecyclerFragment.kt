package com.example.kotlin_materialdesign_nasa.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.databinding.FragmentRecyclerBinding
import com.example.kotlin_materialdesign_nasa.utils.EARTH_R
import com.example.kotlin_materialdesign_nasa.utils.HEADER_R
import com.example.kotlin_materialdesign_nasa.utils.MARS_R


class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding: FragmentRecyclerBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arrayListOf(
            Data("Header","", HEADER_R),
            Data("Earth","Earth drs", EARTH_R),
            Data("Earth","Earth drs", EARTH_R),
            Data("Mars","Mars drs", MARS_R),
            Data("Earth","Earth drs", EARTH_R),
            Data("Earth","Earth drs", EARTH_R),
            Data("Earth","Earth drs", EARTH_R),
            Data("Mars","Mars drs", MARS_R)
        )
        binding.recyclerView.adapter = RecyclerViewAdapter(list)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }
}