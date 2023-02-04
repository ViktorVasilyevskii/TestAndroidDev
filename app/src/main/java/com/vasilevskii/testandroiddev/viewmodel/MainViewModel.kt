package com.vasilevskii.testandroiddev.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasilevskii.testandroiddev.app.App
import com.vasilevskii.testandroiddev.data.ItemRating
import com.vasilevskii.testandroiddev.logic.ObservableProgress
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val app = App()

    val responseServer = MutableLiveData<List<ItemRating>>()
    private val listRatings = mutableListOf<ItemRating>()
    private var valueProgress = 0
    val observableProgress = ObservableProgress()

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    fun getRatings() = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        app.getRetrofitClient().getApiTestAndroidData().getRatings().let { response ->
            if (response.code() == 200) {
                response.body().let {resultRatingsList ->
                    resultRatingsList?.ratings?.x0?.let { listRatings.add(it) }
                    changeProgress()
                    resultRatingsList?.ratings?.x1?.let { listRatings.add(it) }
                    changeProgress()
                    resultRatingsList?.ratings?.x2?.let { listRatings.add(it) }
                    changeProgress()
                    resultRatingsList?.ratings?.x3?.let { listRatings.add(it) }
                    changeProgress()
                    resultRatingsList?.ratings?.x4?.let { listRatings.add(it) }
                    changeProgress()
                    resultRatingsList?.ratings?.x5?.let { listRatings.add(it) }
                    changeProgress()
                    resultRatingsList?.ratings?.x6?.let { listRatings.add(it) }
                    changeProgress()
                    resultRatingsList?.ratings?.x7?.let { listRatings.add(it) }
                    changeProgress()
                    resultRatingsList?.ratings?.x8?.let { listRatings.add(it) }
                    changeProgress()
                }
            } else {
                response.errorBody().let { error ->
                    Log.d("Error", error.toString())
                }
            }
        }
        responseServer.postValue(listRatings)
    }

    private fun changeProgress() {
        valueProgress += 11
        if (valueProgress > 90) valueProgress = 100
        viewModelScope.launch(Dispatchers.Main) {
            observableProgress.value = valueProgress
        }

    }
}