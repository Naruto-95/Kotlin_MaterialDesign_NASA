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

    fun sendRequest() {
       // LiveData.postValue(PictureOfTheDataAppState.Loading(sfsdfs))
       LiveData.postValue(PictureOfTheDataAppState.Loading)
        pictureOfTheDataRetrofitImpl.getRetrofit().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callbak)

    }
    val callbak = object : Callback<PictureOfTheResponseData>{
        override fun onResponse(
            call: Call<PictureOfTheResponseData>,
            response: Response<PictureOfTheResponseData>
        ) {
            if (response.isSuccessful) {
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