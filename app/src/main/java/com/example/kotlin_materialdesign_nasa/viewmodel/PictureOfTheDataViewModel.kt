package com.example.kotlin_materialdesign_nasa.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_materialdesign_nasa.BuildConfig
import com.example.kotlin_materialdesign_nasa.repository.PictureOfTheResponseData
import com.example.kotlin_materialdesign_nasa.repository.PictureOfTheRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class PictureOfTheDataViewModel(
    private val LiveData: MutableLiveData<PictureOfTheDataAppState> = MutableLiveData(),
    private val pictureOfTheDataRetrofitImpl: PictureOfTheRetrofitImpl = PictureOfTheRetrofitImpl()


) :
    ViewModel() {
    fun getLiveData() = LiveData


    fun sendServerRequest(data: String) {
        LiveData.postValue(PictureOfTheDataAppState.Loading)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            LiveData.postValue(PictureOfTheDataAppState.Error(SocketTimeoutException("dsfsfd")))
        } else {
            pictureOfTheDataRetrofitImpl.getRetrofit().getPictureOfTheDay(apiKey, data)
                .enqueue(callbak)
        }
    }

    fun sendRequest() {
        LiveData.postValue(PictureOfTheDataAppState.Loading)
        pictureOfTheDataRetrofitImpl.getRetrofit().getPictureOfTheDay(BuildConfig.NASA_API_KEY)
            .enqueue(callbak)

    }

    val callbak = object : Callback<PictureOfTheResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheResponseData>,
            response: Response<PictureOfTheResponseData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    LiveData.postValue(PictureOfTheDataAppState.Success(it))
                }
            } else {
                LiveData.postValue(PictureOfTheDataAppState.Error(SocketTimeoutException("dsfsfd")))
            }
        }

        override fun onFailure(call: Call<PictureOfTheResponseData>, t: Throwable) {
            PictureOfTheDataAppState.Error(t)
        }

    }

}
