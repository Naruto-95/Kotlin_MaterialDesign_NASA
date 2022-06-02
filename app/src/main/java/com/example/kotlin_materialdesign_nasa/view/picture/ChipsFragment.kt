package com.example.kotlin_materialdesign_nasa.view.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentChipsBinding
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataAppState
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataViewModel
import java.text.SimpleDateFormat
import java.util.*

class ChipsFragment : Fragment() {

    private var _binding: FragmentChipsBinding? = null
    private val binding: FragmentChipsBinding
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
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.yest ->{viewModel.sendServerRequest(takeDate(-1))}
                R.id.befYest ->{viewModel.sendServerRequest(takeDate(-1))}
                R.id.tod->{viewModel.sendRequest()}

            }
        }

        btnBack()


    }


    private fun takeDate(count: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("EST")
        return format.format(currentDate.time)
    }


    private fun renderData(pictureOfTheDataAppState: PictureOfTheDataAppState) {
        when (pictureOfTheDataAppState) {
            is PictureOfTheDataAppState.Success -> {
                binding.imageViewT.load(pictureOfTheDataAppState.pictureOfTheResponseData.hdurl)
            }
            is PictureOfTheDataAppState.Loading -> {
                binding.imageViewT.load(R.drawable.ic_no_photo_vector)

            }
            is PictureOfTheDataAppState.Error -> {
                pictureOfTheDataAppState.error.message

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