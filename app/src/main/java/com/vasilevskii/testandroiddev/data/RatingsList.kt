package com.vasilevskii.testandroiddev.data


import com.google.gson.annotations.SerializedName

data class RatingsList(
    @SerializedName("raitings")
    val ratings: Ratings,
)