package com.vasilevskii.testandroiddev.retrofit

import com.vasilevskii.testandroiddev.Const
import com.vasilevskii.testandroiddev.api.TestAndroidData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private var createRetrofitClient: Retrofit
    private var apiTestAndroidData: TestAndroidData

    init {
        createRetrofitClient = createRetrofitClient()
        apiTestAndroidData = createRetrofitClient.create(TestAndroidData::class.java)
    }

    fun getApiTestAndroidData(): TestAndroidData {
        return apiTestAndroidData
    }

    private fun createRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}