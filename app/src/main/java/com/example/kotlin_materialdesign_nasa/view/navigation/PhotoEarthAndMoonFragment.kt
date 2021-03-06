package com.example.kotlin_materialdesign_nasa.view.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kotlin_materialdesign_nasa.BuildConfig
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentPhotoEarthAndMoonBinding
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataAppState
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataViewModel


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

    private val viewModel: PictureOfTheDataViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDataViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendRequestEpicEarth()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoEarthAndMoonBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun renderData(pictureOfTheDataAppState: PictureOfTheDataAppState) {
        when (pictureOfTheDataAppState) {
            is PictureOfTheDataAppState.SuccessEarth -> {
                binding.loading.visibility = View.GONE
                // binding.imageViewE.load(pictureOfTheDataAppState.pictureEpicEarthResponseData.caption)
                val strDate =
                    pictureOfTheDataAppState.pictureEpicEarthResponseData.last().date.split(" ")
                        .first()
                val image = pictureOfTheDataAppState.pictureEpicEarthResponseData.last().image
                val url = "https://api.nasa.gov/EPIC/archive/natural/" +
                        strDate.replace("-", "/", true) +
                        "/png/" +
                        "$image" +
                        ".png?api_key=${BuildConfig.NASA_API_KEY}"
                binding.imageViewE.load(url)

            }
            is PictureOfTheDataAppState.Loading -> {
                binding.loading.visibility = View.VISIBLE
                binding.imageViewE.load(R.drawable.ic_no_photo_vector)
            }
            is PictureOfTheDataAppState.Error -> {
                binding.loading.visibility = View.GONE
                pictureOfTheDataAppState.error.message

            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = PhotoEarthAndMoonFragment()
    }
}