package com.example.kotlin_materialdesign_nasa.view.picture

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kotlin_materialdesign_nasa.databinding.FragmentPictureBinding
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataAppState
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataViewModel

class PictureFragment : Fragment() {

    private var _binding: FragmentPictureBinding? = null
    private val binding: FragmentPictureBinding
        get() {
            return _binding!!
        }

    private val viewModel: PictureOfTheDataViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDataViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendRequest()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun renderData(pictureOfTheDataAppState: PictureOfTheDataAppState) {
        when (pictureOfTheDataAppState) {
            is PictureOfTheDataAppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.imageView.load(pictureOfTheDataAppState.pictureOfTheResponseData.url)
            }
            is PictureOfTheDataAppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE

            }
            is PictureOfTheDataAppState.Error -> {
                binding.loadingLayout.visibility = View.GONE

            }
        }

    }




    companion object {

        @JvmStatic
        fun newInstance() = PictureFragment()

    }
}