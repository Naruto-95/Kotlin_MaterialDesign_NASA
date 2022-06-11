package com.example.kotlin_materialdesign_nasa.view


import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.databinding.ActivityMainBinding
import com.example.kotlin_materialdesign_nasa.utils.*
import com.example.kotlin_materialdesign_nasa.view.navigation.MyFragment
import com.example.kotlin_materialdesign_nasa.view.picture.PictureFragment


class MainActivity : AppCompatActivity() {
val der = 2000L
    var isOpen: Boolean = false
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(
            MODE_NIGHT_AUTO_BATTERY
        )
        setTheme(getChoosingStyle(getStyleTheme()))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // if (savedInstanceState == null) {
       //     supportFragmentManager.beginTransaction()
      //          .replace(R.id.container, PictureFragment.newInstance()).addToBackStack("").commit()
      //  }
binding.fab.setOnClickListener{
    isOpen = !isOpen
    if (isOpen){
        ObjectAnimator.ofFloat(binding.plus, View.ROTATION,0f,225f).setDuration(der).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, -130f)
            .setDuration(der)
            .start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, -250f)
            .setDuration(der)
            .start()
    }else{
        ObjectAnimator.ofFloat(binding.plus, View.ROTATION,225f,0f).setDuration(der).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y,130f).setDuration(der).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y,250f).setDuration(der).start()
    }
}

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom_navigation,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_one->{
                Toast.makeText(this, "1", Toast.LENGTH_LONG).show()

            }
            R.id.navigation_two->{
                Toast.makeText(this, "1", Toast.LENGTH_LONG).show()

            }
            R.id.navigation_photo->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container,MyFragment.newInstance()).addToBackStack("").commit()
            }
            R.id.navigation_one->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container,PictureFragment.newInstance()).addToBackStack("").commit()
            }

        }
        return super.onOptionsItemSelected(item)
    }


    fun setStyleTheme(styleTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_THEME, styleTheme)
        editor.apply()
    }

    fun getStyleTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_THEME, mainTheme)
    }

    private fun getChoosingStyle(styleTheme: Int): Int {
        return when (styleTheme) {
            mainTheme -> R.style.mPurpleStyle
            redTheme -> R.style.mRedStyle
            greenTheme -> R.style.mGreenStyle
            else -> mainTheme


        }

    }

}





