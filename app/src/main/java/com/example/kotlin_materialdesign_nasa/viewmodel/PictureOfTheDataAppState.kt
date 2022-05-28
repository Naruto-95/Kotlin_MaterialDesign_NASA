package com.example.kotlin_materialdesign_nasa.viewmodel

import com.example.kotlin_materialdesign_nasa.repository.PictureOfTheResponseData

sealed class PictureOfTheDataAppState {
    data class Error(val error: Throwable): PictureOfTheDataAppState()
    data class Success(val pictureOfTheResponseData: PictureOfTheResponseData): PictureOfTheDataAppState()
    object Loading:PictureOfTheDataAppState()
}