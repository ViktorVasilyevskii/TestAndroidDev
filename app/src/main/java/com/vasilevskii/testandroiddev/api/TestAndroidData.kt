package com.vasilevskii.testandroiddev.api

import com.vasilevskii.testandroiddev.data.RatingsList
import retrofit2.Response
import retrofit2.http.GET

interface TestAndroidData {

    @GET("testAndroidData")
    suspend fun getRatings(): Response<RatingsList>
}