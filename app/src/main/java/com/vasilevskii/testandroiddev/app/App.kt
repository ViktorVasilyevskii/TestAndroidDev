package com.vasilevskii.testandroiddev.app

import android.app.Application
import com.vasilevskii.testandroiddev.retrofit.RetrofitClient

class App : Application() {

    private var retrofitClient: RetrofitClient = RetrofitClient()

    fun getRetrofitClient(): RetrofitClient {
        return when(retrofitClient) {
            null -> RetrofitClient()
            else -> retrofitClient
        }
    }

}