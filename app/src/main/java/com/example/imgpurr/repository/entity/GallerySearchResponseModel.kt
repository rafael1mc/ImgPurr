package com.example.imgpurr.repository.entity

import com.google.gson.annotations.SerializedName

data class GallerySearchResponseModel(
    @SerializedName("data") val data: List<GalleryResponseModel>,
    @SerializedName("success") val success: Boolean,
    @SerializedName("status") val status: String
)