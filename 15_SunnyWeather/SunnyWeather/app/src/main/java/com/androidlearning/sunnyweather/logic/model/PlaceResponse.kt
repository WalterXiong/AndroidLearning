package com.androidlearning.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    val status: String,
    val count: Int,
    val info: String,
    val geocodes: List<Place>
)

data class Place(
    val country: String,
    val province: String,
    val city: String,
    val citycode: String,
    val street: String,
    val number: String,
    val adcode: String,
    val level: String,
    val location: String,
    @SerializedName("formatted_address") val address: String
)