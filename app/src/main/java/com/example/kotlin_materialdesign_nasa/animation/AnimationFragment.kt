package com.example.kotlin_materialdesign_nasa.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentAnimationBinding
import com.example.kotlin_materialdesign_nasa.view.picture.PictureFragment

class AnimationFragment : Fragment() {
    val der = 1000L
    var isOpen: Boolean = false

    private var _binding: FragmentAnimationBinding? = null
    private val binding: FragmentAnimationBinding
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
        _binding = FragmentAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animationBtnMain()
        binding.optionTwoContainer.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
                .replace(R.id.container, PictureFragment.newInstance()).addToBackStack("").commit()
        }

    }




    private fun animationBtnMain() {
        binding.fab.setOnClickListener {
            isOpen = !isOpen
            if (isOpen) {
                ObjectAnimator.ofFloat(binding.plus, View.ROTATION, 0f, 225f).setDuration(der)
                    .start()

                ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, -130f)
                    .setDuration(der)
                    .start()


                ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, -250f)
                    .setDuration(der)
                    .start()

                binding.optionOneContainer.animate().alpha(1f).setDuration(der).setListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        binding.optionOneContainer.isClickable = true
                    }
                })

                binding.optionTwoContainer.animate().alpha(1f).setDuration(der).setListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        binding.optionTwoContainer.isClickable = true
                    }
                })
                binding.transparentBackground.animate().alpha(0.5f).setDuration(der).setListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        binding.transparentBackground.isClickable = false
                    }
                })
            } else {
                ObjectAnimator.ofFloat(binding.plus, View.ROTATION, 225f, 0f).setDuration(der)
                    .start()
                ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, 0f)
                    .setDuration(der).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, 0f)
                    .setDuration(der).start()

                binding.optionOneContainer.animate().alpha(0f).setDuration(der).setListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        binding.optionOneContainer.isClickable = false
                    }
                })

                binding.optionTwoContainer.animate().alpha(0f).setDuration(der).setListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        binding.optionTwoContainer.isClickable = false
                    }
                })

                binding.transparentBackground.animate().alpha(0f).setDuration(der).setListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        binding.transparentBackground.isClickable = false
                    }
                })
            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = AnimationFragment()

    }
}