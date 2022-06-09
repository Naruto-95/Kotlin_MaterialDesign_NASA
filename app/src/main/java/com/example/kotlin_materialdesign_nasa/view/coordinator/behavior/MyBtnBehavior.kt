package com.example.kotlin_materialdesign_nasa.view.coordinator.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class MyBtnBehavior(
    context: Context,
    arrts: AttributeSet? = null,
) : CoordinatorLayout.Behavior<View>(context, arrts) {



    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return (dependency is AppBarLayout)
    }




    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (dependency is AppBarLayout) {
            val bar = dependency
            child.alpha = (2 - abs(5.8 * bar.y) / bar.height.toFloat()).toFloat()
        }else{

        }
        return super.onDependentViewChanged(parent, child, dependency)
    }

}