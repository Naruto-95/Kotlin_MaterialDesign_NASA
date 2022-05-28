package com.example.kotlin_materialdesign_nasa.r.rquest.viewmodelrequest

import com.example.kotlin_materialdesign_nasa.r.rquest.NasaDTO

sealed class PictureDataAppState {
    data class Error(val error: Throwable): PictureDataAppState()
    data class Success(val pictureOfTheResponseData2: NasaDTO): PictureDataAppState()
    object Loading:PictureDataAppState()
}