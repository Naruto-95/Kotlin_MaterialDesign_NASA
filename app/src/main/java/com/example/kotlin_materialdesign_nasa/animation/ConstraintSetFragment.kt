package com.example.kotlin_materialdesign_nasa.animation

import android.os.Bundle
import android.text.TextUtils.replace
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.FragmentConstraintSetStartBinding
import com.example.kotlin_materialdesign_nasa.view.picture.PictureFragment

class ConstraintSetFragment : Fragment() {

    var isOpen: Boolean = false

    private var _binding: FragmentConstraintSetStartBinding? = null
    private val binding: FragmentConstraintSetStartBinding
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
        _binding = FragmentConstraintSetStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        maketAnimation()
        animationFragments()




    }

    private fun animationFragments() {
        binding.wikiButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                .replace(R.id.container, PictureFragment.newInstance()).addToBackStack("").commit()
        }
    }


    private fun maketAnimation() {
        binding.text.setOnClickListener {

            val constraintSet = ConstraintSet()
            val transition = ChangeBounds()
            transition.duration = 1000L
            transition.interpolator = AnticipateOvershootInterpolator(1.2f)
            TransitionManager.beginDelayedTransition(binding.constraintOntainer, transition)
            isOpen = !isOpen
            if (isOpen) {
                constraintSet.clone(requireContext(), R.layout.fragment_constraint_set_end)
            } else {
                constraintSet.clone(requireContext(), R.layout.fragment_constraint_set_start)
            }



            constraintSet.applyTo(binding.constraintOntainer)
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = ConstraintSetFragment()

    }
}

