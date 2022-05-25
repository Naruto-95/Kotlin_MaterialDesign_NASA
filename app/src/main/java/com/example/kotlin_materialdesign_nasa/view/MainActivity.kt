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
}




