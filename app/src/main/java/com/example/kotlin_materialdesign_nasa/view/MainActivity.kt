package com.example.kotlin_materialdesign_nasa.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_materialdesign_nasa.R
import com.example.kotlin_materialdesign_nasa.view.picture.PictureFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
           supportFragmentManager.beginTransaction().replace(R.id.container,PictureFragment.newInstance()).commit()
        }





    }

/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_plug, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.s -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, Chips.newInstance())
                    .addToBackStack("")
                    .commit()
            }

        }


        return super.onOptionsItemSelected(item)
    }*/
}




