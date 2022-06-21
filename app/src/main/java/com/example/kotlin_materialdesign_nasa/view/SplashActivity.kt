package com.example.kotlin_materialdesign_nasa.view


import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.animation.AnimationFragment
import com.example.kotlin_materialdesign_nasa.animation.ConstraintSetFragment
import com.example.kotlin_materialdesign_nasa.animation.ShadowAnimationFragment
import com.example.kotlin_materialdesign_nasa.databinding.ActivityMainBinding
import com.example.kotlin_materialdesign_nasa.databinding.ActivitySplashBinding
import com.example.kotlin_materialdesign_nasa.recycler.RecyclerFragment
import com.example.kotlin_materialdesign_nasa.utils.*
import com.example.kotlin_materialdesign_nasa.view.navigation.MyFragment
import com.example.kotlin_materialdesign_nasa.view.picture.PictureFragment
import space.SpaceNotesFragment


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            ObjectAnimator.ofFloat(binding.imageView, View.ROTATION, 720f).setDuration(5000).start()
            Handler(mainLooper).postDelayed({
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))

                finish()
            }, 3000)

        }//


    }


}





