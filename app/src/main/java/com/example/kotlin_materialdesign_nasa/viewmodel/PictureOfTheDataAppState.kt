package com.example.kotlin_materialdesign_nasa.viewmodel

import com.example.kotlin_materialdesign_nasa.repository.dto.PictureEpicEarthResponseData
import com.example.kotlin_materialdesign_nasa.repository.dto.PictureMarsResponse
import com.example.kotlin_materialdesign_nasa.repository.dto.PictureOfTheResponseData

sealed class PictureOfTheDataAppState {


    data class Error(val error: Throwable) : PictureOfTheDataAppState()
    data class SuccessEarth(val pictureEpicEarthResponseData: List<PictureEpicEarthResponseData>) :
        PictureOfTheDataAppState()

    data class Success(val pictureOfTheResponseData: PictureOfTheResponseData) :
        PictureOfTheDataAppState()

    data class SuccessMars(val pictureMarsResponse: PictureMarsResponse) :
        PictureOfTheDataAppState()

    object Loading : PictureOfTheDataAppState()
}