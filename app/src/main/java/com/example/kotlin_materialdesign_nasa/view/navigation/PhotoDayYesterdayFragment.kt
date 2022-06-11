package com.example.kotlin_materialdesign_nasa.view.navigation

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentPhotoDayYesterdayBinding
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataAppState
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataViewModel


class PhotoDayYesterdayFragment : Fragment() {


    var isOpen: Boolean = false
    private var _binding: FragmentPhotoDayYesterdayBinding? = null
    private val binding: FragmentPhotoDayYesterdayBinding
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

        OnClikChip()
        imageAnimation()
    }

    private fun imageAnimation() {
        binding.imageView.setOnClickListener {
            isOpen = !isOpen
            val transitionImage = ChangeImageTransform()
            transitionImage.duration = 2000
            binding.imageView.scaleType =
                if (isOpen) {
                    ImageView.ScaleType.CENTER_CROP
                } else {
                    ImageView.ScaleType.CENTER_INSIDE
                }
            TransitionManager.beginDelayedTransition(binding.animationContainer, transitionImage)


        }
    }

    private fun OnClikChip() {
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yest -> {
                    binding.chipGroup.check(R.id.yest)
                    viewModel.sendRequest((YESTERDAY))
                }
                R.id.tod -> {
                    binding.chipGroup.check(R.id.tod)
                    viewModel.sendRequest((TODAY))
                }

            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoDayYesterdayBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun renderData(pictureOfTheDataAppState: PictureOfTheDataAppState) {
        when (pictureOfTheDataAppState) {
            is PictureOfTheDataAppState.Success -> {
                binding.loading.visibility = View.GONE
                binding.imageView.load(pictureOfTheDataAppState.pictureOfTheResponseData.hdurl) {
                    binding.title.text =
                        pictureOfTheDataAppState.pictureOfTheResponseData.title
                    binding.explanation.text =
                        pictureOfTheDataAppState.pictureOfTheResponseData.explanation
                    val transition = ChangeBounds()
                    transition.duration = 3000
                    TransitionManager.beginDelayedTransition(binding.animationContainer, transition)


                }
            }
            is PictureOfTheDataAppState.Loading -> {
                binding.loading.visibility = View.VISIBLE
                binding.imageView.load(R.drawable.ic_no_photo_vector)
            }
            is PictureOfTheDataAppState.Error -> {
                binding.loading.visibility = View.GONE
                pictureOfTheDataAppState.error.message

            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = PhotoDayYesterdayFragment()
        private const val TODAY = 0
        private const val YESTERDAY = 1
    }
}