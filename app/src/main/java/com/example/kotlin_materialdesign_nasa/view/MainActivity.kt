package com.example.kotlin_materialdesign_nasa.view


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.animation.AnimationFragment
import com.example.kotlin_materialdesign_nasa.animation.ConstraintSetFragment
import com.example.kotlin_materialdesign_nasa.animation.ShadowAnimationFragment
import com.example.kotlin_materialdesign_nasa.databinding.ActivityMainBinding
import com.example.kotlin_materialdesign_nasa.recycler.RecyclerFragment
import com.example.kotlin_materialdesign_nasa.utils.*
import com.example.kotlin_materialdesign_nasa.view.navigation.MyFragment
import com.example.kotlin_materialdesign_nasa.view.picture.PictureFragment
import space.SpaceNotesFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(
            MODE_NIGHT_AUTO_BATTERY
        )
        setTheme(getChoosingStyle(getStyleTheme()))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureFragment.newInstance()).addToBackStack("").commit()
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





