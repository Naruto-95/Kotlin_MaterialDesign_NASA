package com.example.kotlin_materialdesign_nasa.view.coordinator.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class MyBtnBehavior(
    context: Context,
    arrts: AttributeSet? = null,
) : CoordinatorLayout.Behavior<View>(context, arrts) {
    private var upReach = false

    // После того, как список проведен вверх, затем проведите вниз, чтобы достичь критической точки общего скольжения интерфейса
    private var downReach = false
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return (dependency is AppBarLayout)
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: View,
        ev: MotionEvent
    ): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downReach = false
                upReach = false
            }
        }
        return super.onInterceptTouchEvent(parent, child, ev)
    }



    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (dependency is AppBarLayout) {
            val bar = dependency
           // child.x =
               // bar.width.toFloat() - bar.width.toFloat() * (1 - abs(2 * bar.y) / bar.height.toFloat())
            child.alpha = 1 - abs(2 * bar.y) / bar.height.toFloat()

        }
        return super.onDependentViewChanged(parent, child, dependency)
    }

}