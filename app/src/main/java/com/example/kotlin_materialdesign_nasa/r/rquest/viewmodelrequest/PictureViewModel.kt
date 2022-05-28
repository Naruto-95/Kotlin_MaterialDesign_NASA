package com.example.kotlin_materialdesign_nasa.r.rquest.viewmodelrequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_materialdesign_nasa.BuildConfig
import com.example.kotlin_materialdesign_nasa.r.rquest.NasaDTO
import com.example.kotlin_materialdesign_nasa.r.rquest.PictureOfTheRetrofitImplTwo
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class PictureViewModel(
    private val LiveData: MutableLiveData<PictureDataAppState> = MutableLiveData(),
    private val pictureOfTheDataRetrofitImpl2: PictureOfTheRetrofitImplTwo = PictureOfTheRetrofitImplTwo()


) :
    ViewModel() {
    fun getLiveData() = LiveData
    fun sendRequestToday() {
        pictureOfTheDataRetrofitImpl2.getRetrofit2()
            .getPictureThreeDaysAPI(BuildConfig.NASA_API_KEY).enqueue(callback)

    }

    fun sendRequestYesterday() {
        pictureOfTheDataRetrofitImpl2.getRetrofit2()
            .getPictureThreeDaysAPI(BuildConfig.NASA_API_KEY).enqueue(callback)

    }

    fun sendRequestTDBY() {
        pictureOfTheDataRetrofitImpl2.getRetrofit2()
            .getPictureThreeDaysAPI(BuildConfig.NASA_API_KEY).enqueue(callback)

    }

    val callback = object : Callback<NasaDTO> {
        override fun onResponse(call: retrofit2.Call<NasaDTO>, response: Response<NasaDTO>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    LiveData.postValue(PictureDataAppState.Success(it))
                }
            } else {
                LiveData.postValue(PictureDataAppState.Error(SocketTimeoutException("dsfsfd")))
            }



        }

        override fun onFailure(call: retrofit2.Call<NasaDTO>, t: Throwable) {
            PictureDataAppState.Error(t)
        }
    }
}




