package com.example.kotlin_materialdesign_nasa.repository

import com.example.kotlin_materialdesign_nasa.utils.NASA_BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PictureOfTheRetrofitImpl {


    private val nasaBaseUrl = NASA_BASE_URL
    fun getRetrofit(): PictureOfTheDayAPI {
        val pictureOfTheRetrofitImpl = Retrofit.Builder()
            .baseUrl(nasaBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return pictureOfTheRetrofitImpl.create(PictureOfTheDayAPI::class.java)

    }



}