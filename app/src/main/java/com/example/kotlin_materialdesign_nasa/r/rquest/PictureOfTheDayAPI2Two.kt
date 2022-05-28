package com.example.kotlin_materialdesign_nasa.r.rquest

import com.example.kotlin_materialdesign_nasa.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI2Two {

    @GET(PLANETARY_APOD )
    fun getPictureThreeDaysAPI(@Query(API_KEY)api_key:String?): Call<NasaDTO>

}