package com.example.kotlin_materialdesign_nasa.view.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentPhotoMarsAndWeatherBinding
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataAppState
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataViewModel
import com.google.android.material.snackbar.Snackbar


class PhotoMarsAndWeatherFragment : Fragment() {

    private var _binding: FragmentPhotoMarsAndWeatherBinding? = null
    private val binding: FragmentPhotoMarsAndWeatherBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    private val viewModel: PictureOfTheDataViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDataViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendRequestMars()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoMarsAndWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun renderData(pictureOfTheDataAppState: PictureOfTheDataAppState) {
        when (pictureOfTheDataAppState) {
            is PictureOfTheDataAppState.SuccessMars -> {
                binding.imageViewM.load(pictureOfTheDataAppState.pictureMarsResponse.photos)



            }
            is PictureOfTheDataAppState.Loading -> {

                binding.imageViewM.load(R.drawable.ic_no_photo_vector)
            }
            is PictureOfTheDataAppState.Error -> {

                pictureOfTheDataAppState.error.message

            }
        }

    }
    companion object {

        @JvmStatic
        fun newInstance() = PhotoMarsAndWeatherFragment()
    }
}