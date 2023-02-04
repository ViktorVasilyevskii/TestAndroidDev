package com.vasilevskii.testandroiddev.data


import com.google.gson.annotations.SerializedName

data class ItemRating(
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
)