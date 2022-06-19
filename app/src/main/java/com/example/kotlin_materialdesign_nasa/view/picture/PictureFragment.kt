package com.example.kotlin_materialdesign_nasa.view.picture

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.TextAppearanceSpan
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentPictureBinding
import com.example.kotlin_materialdesign_nasa.view.MainActivity
import com.example.kotlin_materialdesign_nasa.view.coordinator.ChipsFragment
import com.example.kotlin_materialdesign_nasa.view.settings.SettingsFragment
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataAppState
import com.example.kotlin_materialdesign_nasa.viewmodel.PictureOfTheDataViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PictureFragment : Fragment() {
    var isOpen: Boolean = false
    private var isMenu = true
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
                Toast.makeText(context, "избранное", Toast.LENGTH_LONG).show()
            }

            R.id.app_bar_search -> {
                Toast.makeText(context, "настройки", Toast.LENGTH_LONG).show()
                requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                    .replace(R.id.container, SettingsFragment.newInstance()).addToBackStack("")
                    .commit()
            }
            R.id.s -> {
                requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                    .replace(R.id.container, ChipsFragment.newInstance()).addToBackStack("")
                    .commit()
            }
            android.R.id.home -> {
                NavigationFragment.newInstance().show(requireActivity().supportFragmentManager, "")

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
    ): View? {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        main()
        viewModel.sendRequest(BEFORE_YESTERDAY)
        wikipediaClick()
        behaviour()
        switching()
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
            TransitionManager.beginDelayedTransition(binding.picture, transitionImage)


        }
    }

    private fun OnClikChip() {
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.befYest -> {
                    binding.chipGroup.check(R.id.befYest)
                    viewModel.sendRequest((BEFORE_YESTERDAY))
                }
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


    private fun switching() {
        binding.fab.setOnClickListener {
            if (isMenu) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_plug)


            } else {
                binding.bottomAppBar.navigationIcon = (ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_menu_24
                ))
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
            isMenu = !isMenu
        }

    }

    //уловить состояние
    private fun behaviour() {
        val bottomsheet = BottomSheetBehavior.from(binding.btnSheet.bottomSheetContainer)
        bottomsheet.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        bottomsheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        Log.d("111", "STATE_DRAGGING")
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Log.d("111", "STATE_COLLAPSED")
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        Log.d("111", "STATE_EXPANDED")
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        Log.d("111", "STATE_HALF_EXPANDED")
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        Log.d("111", "STATE_HIDDEN")
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        Log.d("111", "STATE_SETTLING")

                    }
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
                //binding.loading.visibility = View.GONE
                binding.imageView.load(pictureOfTheDataAppState.pictureOfTheResponseData.hdurl) {
                    binding.btnSheet.title.text =
                        pictureOfTheDataAppState.pictureOfTheResponseData.title
                    val textSpannable =
                        pictureOfTheDataAppState.pictureOfTheResponseData.explanation
                    val Spannable = SpannableString(textSpannable)


                    Spannable.setSpan(
                        ForegroundColorSpan(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.Red

                            )
                        ),
                        0, Spannable.length, SpannableString.SPAN_EXCLUSIVE_INCLUSIVE
                    )


                    // Spannable.setSpan(TextAppearanceSpan(context,R.style.FooTextAppearance),0,7,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)


                    binding.btnSheet.title.text = Spannable



                }
            }
            is PictureOfTheDataAppState.Loading -> {
                //binding.loading.visibility = View.VISIBLE
                binding.imageView.load(R.drawable.ic_no_photo_vector)
            }
            is PictureOfTheDataAppState.Error -> {
                // binding.loading.visibility = View.GONE
                pictureOfTheDataAppState.error.message

            }
        }

    }


    companion object {

        @JvmStatic
        fun newInstance() = PictureFragment()
        private const val TODAY = 0
        private const val YESTERDAY = 1
        private const val BEFORE_YESTERDAY = 2
    }
}