package com.example.kotlin_materialdesign_nasa.view.picture

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kotlin_materialdesign_nasa.databinding.FragmentChipsBinding
import com.example.kotlin_materialdesign_nasa.r.rquest.viewmodelrequest.PictureDataAppState
import com.example.kotlin_materialdesign_nasa.r.rquest.viewmodelrequest.PictureViewModel
import com.google.android.material.chip.Chip

class ChipsFragment : Fragment() {

    private var _binding: FragmentChipsBinding? = null
    private val binding: FragmentChipsBinding
        get() {
            return _binding!!
        }

    private val viewModel: PictureViewModel by lazy {
        ViewModelProvider(this).get(PictureViewModel::class.java)
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
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendRequestToday()
        viewModel.sendRequestYesterday()
        viewModel.sendRequestTDBY()

        btnBack()


    }


    fun chips() {

    }


    fun renderData(pictureDataAppState: PictureDataAppState) {
        when (pictureDataAppState) {
            is PictureDataAppState.Success -> {
                binding.chipGroup.setOnCheckedChangeListener { group, position ->
                    when (position) {
                        1 -> {
                            viewModel.sendRequestToday()
                            binding.imageViewT.load(pictureDataAppState.pictureOfTheResponseData2.url) {

                            }
                        }
                        2 -> {
                            viewModel.sendRequestYesterday()
                            binding.imageViewT.load(pictureDataAppState.pictureOfTheResponseData2.hdurl)
                        }
                        3 -> {
                            viewModel.sendRequestTDBY()
                            binding.imageViewT.load(pictureDataAppState.pictureOfTheResponseData2.url)

                        }
                    }
                    group.findViewById<Chip>(position)?.let {
                        Log.d("111", "${it.text.toString()}")

                    }
                }


            }

            is PictureDataAppState.Loading -> {
                //  binding.loading.visibility = View.VISIBLE

            }
            is PictureDataAppState.Error -> {
                // binding.loading.visibility = View.GONE

            }

        }

    }


    private fun btnBack() {
        binding.back.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = ChipsFragment()

    }
}