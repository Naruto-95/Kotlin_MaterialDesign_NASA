package com.example.kotlin_materialdesign_nasa.r.rquest


import com.example.kotlin_materialdesign_nasa.utils.NASA_BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PictureOfTheRetrofitImplTwo {


    private val nasaBaseUrl = NASA_BASE_URL
    fun getRetrofit2(): PictureOfTheDayAPI2Two {
        val pictureOfTheRetrofitImpl2 = Retrofit.Builder()
            .baseUrl(nasaBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return pictureOfTheRetrofitImpl2.create(PictureOfTheDayAPI2Two::class.java)

    }

}