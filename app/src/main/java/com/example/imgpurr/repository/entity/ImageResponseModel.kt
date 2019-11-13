package com.example.imgpurr.repository.entity

import com.google.gson.annotations.SerializedName

data class ImageResponseModel(
    @SerializedName("id") val id: String,
    @SerializedName("link") val link: String
)