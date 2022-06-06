package com.example.kotlin_materialdesign_nasa.repository

import com.example.kotlin_materialdesign_nasa.repository.dto.*
import com.example.kotlin_materialdesign_nasa.utils.NASA_BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Callback
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


    fun getPictureOfTheDay(apiKey: String, date: String, podCallback: Callback<PictureOfTheResponseData>) {
        getRetrofit().getPictureOfTheDay(apiKey, date).enqueue(podCallback)
    }
    fun getEarth(apiKey: String,earthCallback: Callback<List<PictureEpicEarthResponseData>>) {
        getRetrofit().getEarth(apiKey).enqueue(earthCallback)
    }

    fun getMarsPhoto(earth_date:String, apiKey: String, marsCallback: Callback<PictureMarsResponse>) {
        getRetrofit().getMarsPhoto(apiKey,earth_date).enqueue(marsCallback)
    }
}



