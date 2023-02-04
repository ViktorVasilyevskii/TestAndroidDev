package com.vasilevskii.testandroiddev.data


import com.google.gson.annotations.SerializedName

data class Ratings(
    @SerializedName("0")
    val x0: ItemRating,
    @SerializedName("1")
    val x1: ItemRating,
    @SerializedName("2")
    val x2: ItemRating,
    @SerializedName("3")
    val x3: ItemRating,
    @SerializedName("4")
    val x4: ItemRating,
    @SerializedName("5")
    val x5: ItemRating,
    @SerializedName("6")
    val x6: ItemRating,
    @SerializedName("7")
    val x7: ItemRating,
    @SerializedName("8")
    val x8: ItemRating,
)