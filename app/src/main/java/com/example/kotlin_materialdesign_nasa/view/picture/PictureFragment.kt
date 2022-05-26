package com.example.kotlin_materialdesign_nasa.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentPictureBinding
import com.example.kotlin_materialdesign_nasa.view.MainActivity
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataAppState
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar

class PictureFragment : Fragment() {

    private var _binding: FragmentPictureBinding? = null
    private val binding: FragmentPictureBinding
        get() {
            return _binding!!
        }

    private val viewModel: PictureOfTheDataViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDataViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bottom_bar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> {
                binding.bottomAppBar
                Toast.makeText(context, "избранное", Toast.LENGTH_LONG).show()
            }

            R.id.app_bar_search -> {
                Toast.makeText(context, "настройки", Toast.LENGTH_LONG).show()
            }
            android.R.id.home->{
                NavigationFragment.newInstance().show(requireActivity().supportFragmentManager,"")

            }
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        main()
        viewModel.sendRequest()
        wikipediaClick()
        behaviour()
    }
//уловить состояние
    private fun behaviour() {
        val bottomsheet = BottomSheetBehavior.from(binding.btnSheet.bottomSheetContainer)
        bottomsheet.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        bottomsheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_COLLAPSED -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                    BottomSheetBehavior.STATE_SETTLING -> {}
                }

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        })
    }

    private fun main() {
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    private fun wikipediaClick() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })

        }


    }


    private fun renderData(pictureOfTheDataAppState: PictureOfTheDataAppState) {
        when (pictureOfTheDataAppState) {
            is PictureOfTheDataAppState.Success -> {
                binding.loading.visibility = View.GONE
                binding.imageView.load(pictureOfTheDataAppState.pictureOfTheResponseData.hdurl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_cloud)
                    //transformations(CircleCropTransformation())
                    binding.btnSheet.title.text =
                        pictureOfTheDataAppState.pictureOfTheResponseData.title
                    binding.btnSheet.explanation.text =
                        pictureOfTheDataAppState.pictureOfTheResponseData.explanation
                    //binding.btnSheet.imageViewUrl.load(pictureOfTheDataAppState.pictureOfTheResponseData.url)

                }


            }
            is PictureOfTheDataAppState.Loading -> {
                binding.loading.visibility = View.VISIBLE

            }
            is PictureOfTheDataAppState.Error -> {
                binding.loading.visibility = View.GONE

            }
        }

    }


    companion object {

        @JvmStatic
        fun newInstance() = PictureFragment()

    }
}