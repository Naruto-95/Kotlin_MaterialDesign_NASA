package com.example.kotlin_materialdesign_nasa.viewmodel

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_materialdesign_nasa.BuildConfig
import com.example.kotlin_materialdesign_nasa.repository.PictureOfTheRetrofitImpl
import com.example.kotlin_materialdesign_nasa.repository.dto.PictureEpicEarthResponseData
import com.example.kotlin_materialdesign_nasa.repository.dto.PictureMarsResponse
import com.example.kotlin_materialdesign_nasa.repository.dto.PictureOfTheResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class PictureOfTheDataViewModel(
    private val LiveData: MutableLiveData<PictureOfTheDataAppState> = MutableLiveData(),
    private val pictureOfTheDataRetrofitImpl: PictureOfTheRetrofitImpl = PictureOfTheRetrofitImpl()


) :
    ViewModel() {
    fun getLiveData() = LiveData




    fun sendRequest(day:Int) {
        val date = getDate(day)
        LiveData.postValue(PictureOfTheDataAppState.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            PictureOfTheDataAppState.Error(Throwable(API_ERROR))
        } else {
            pictureOfTheDataRetrofitImpl.getPictureOfTheDay(
                apiKey,
                date, callbak
            )

        }
    }


    fun sendRequestMars() {
        LiveData.postValue(PictureOfTheDataAppState.Loading)
        val earthDate = getDayBeforeYesterday()
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            PictureOfTheDataAppState.Error(Throwable(API_ERROR))
        } else {
            pictureOfTheDataRetrofitImpl.getMarsPhoto(
                earthDate,apiKey,
                marsCallbak
            )

        }
    }
    val marsCallbak = object : Callback<PictureMarsResponse> {
        override fun onResponse(
            call: Call<PictureMarsResponse>,
            response: Response<PictureMarsResponse>
        ) {
            if (response.isSuccessful && response.body() != null) {
                LiveData.postValue(PictureOfTheDataAppState.SuccessMars(response.body()!!))

            } else {
                LiveData.postValue(PictureOfTheDataAppState.Error(SocketTimeoutException("dsfsfd")))}

        }

        override fun onFailure(call:  Call<PictureMarsResponse>, t: Throwable) {
            PictureOfTheDataAppState.Error(t)
        }



    }

    fun getDayBeforeYesterday(): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val yesterday = LocalDateTime.now().minusDays(2)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return yesterday.format(formatter)
        } else {
            val cal: Calendar = Calendar.getInstance()
            val s = SimpleDateFormat("yyyy-MM-dd")
            cal.add(Calendar.DAY_OF_YEAR, -2)
            return s.format(cal.time)
        }
    }
    fun sendRequestEpicEarth() {
        LiveData.postValue(PictureOfTheDataAppState.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            PictureOfTheDataAppState.Error(Throwable(API_ERROR))
        } else {
            pictureOfTheDataRetrofitImpl.getEarth(
                apiKey,
                earthCallbak
            )

        }
    }
    val earthCallbak = object : Callback<List<PictureEpicEarthResponseData>> {
        override fun onResponse(
            call: Call<List<PictureEpicEarthResponseData>>,
            response: Response<List<PictureEpicEarthResponseData>>
        ) {
            if (response.isSuccessful && response.body() != null) {
                LiveData.postValue(PictureOfTheDataAppState.SuccessEarth(response.body()!!))

            } else {
                    LiveData.postValue(PictureOfTheDataAppState.Error(SocketTimeoutException("dsfsfd")))}

        }

        override fun onFailure(call:  Call<List<PictureEpicEarthResponseData>>, t: Throwable) {
            PictureOfTheDataAppState.Error(t)
        }



    }
    private fun getDate(day: Int): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val yesterday = LocalDateTime.now().minusDays(day.toLong())
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return yesterday.format(formatter)
        } else {
            val cal: Calendar = Calendar.getInstance()
            val s = SimpleDateFormat("yyyy-MM-dd")
            cal.add(Calendar.DAY_OF_YEAR, (-day))
            return s.format(cal.time)
        }
    }

    val callbak = object : Callback<PictureOfTheResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheResponseData>,
            response: Response<PictureOfTheResponseData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                LiveData.postValue(PictureOfTheDataAppState.Success(response.body()!!))

            } else {
                LiveData.postValue(PictureOfTheDataAppState.Error(SocketTimeoutException("dsfsfd")))
            }
        }

        override fun onFailure(call: Call<PictureOfTheResponseData>, t: Throwable) {
            PictureOfTheDataAppState.Error(t)
        }

    }
        companion object {
            private const val API_ERROR = "You need API Key"
            private const val UNKNOWN_ERROR = "Unidentified error"
        }

}
