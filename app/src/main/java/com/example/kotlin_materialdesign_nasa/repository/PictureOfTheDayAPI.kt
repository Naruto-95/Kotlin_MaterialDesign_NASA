package com.example.kotlin_materialdesign_nasa.repository

import com.example.kotlin_materialdesign_nasa.repository.dto.PictureEpicEarthResponseData
import com.example.kotlin_materialdesign_nasa.repository.dto.PictureOfTheResponseData
import com.example.kotlin_materialdesign_nasa.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {

    @GET(PLANETARY_APOD)
    fun getPictureOfTheDay(
        @Query(API_KEY) apiKey: String,
        @Query(API_DATA) date: String,
    ): Call<PictureOfTheResponseData>


    @GET(KEY_EPIC)
    fun getEarth(@Query(API_KEY) apiKey: String): Call<PictureEpicEarthResponseData>

}