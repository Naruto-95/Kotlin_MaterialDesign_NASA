package com.example.kotlin_materialdesign_nasa.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.utils.*
import com.example.kotlin_materialdesign_nasa.view.picture.PictureFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(
            MODE_NIGHT_AUTO_BATTERY
        )
        setTheme(getChoosingStyle(getStyleTheme()))
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureFragment.newInstance()).addToBackStack("").commit()
        }


    }

    fun setStyleTheme(styleTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_THEME, styleTheme)
        editor.apply()
    }

    fun getStyleTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_THEME, -1)
    }

    private fun getChoosingStyle(styleTheme: Int): Int {
        return when (styleTheme) {
            mainTheme -> R.style.mPurpleStyle
            redTheme -> R.style.mRedStyle
            greenTheme -> R.style.mGreenStyle
            else -> 0
        }

    }

}




