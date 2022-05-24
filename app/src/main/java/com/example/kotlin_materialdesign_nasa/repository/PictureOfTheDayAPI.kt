package com.example.kotlin_materialdesign_nasa.repository

import com.example.kotlin_materialdesign_nasa.utils.API_KEY
import com.example.kotlin_materialdesign_nasa.utils.PLANETARY_APOD
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET(PLANETARY_APOD)
    fun getPictureOfTheDay(@Query(API_KEY)api_key:String?):Call<PictureOfTheResponseData>



}