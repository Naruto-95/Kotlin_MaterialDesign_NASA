package com.example.kotlin_materialdesign_nasa.viewmodel

import com.example.kotlin_materialdesign_nasa.repository.dto.PictureEpicEarthResponseData
import com.example.kotlin_materialdesign_nasa.repository.dto.PictureOfTheResponseData

sealed class PictureOfTheDataAppState {


    data class Error(val error: Throwable): PictureOfTheDataAppState()
    data class SuccessEarth(val pictureEpicEarthResponseData: PictureEpicEarthResponseData): PictureOfTheDataAppState()
    data class Success(val pictureOfTheResponseData: PictureOfTheResponseData): PictureOfTheDataAppState()
    object Loading:PictureOfTheDataAppState()
}