package com.example.kotlin_materialdesign_nasa.viewmodel

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_materialdesign_nasa.BuildConfig
import com.example.kotlin_materialdesign_nasa.repository.PictureOfTheRetrofitImpl
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
                BuildConfig.NASA_API_KEY,
                date, callbak
            )

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
