package com.example.imgpurr.repository.entity

import com.google.gson.annotations.SerializedName

data class GalleryResponseModel(
    @SerializedName("id") val id: String,
    @SerializedName("link") val link: String?,
    @SerializedName("is_album") val isAlbum: Boolean,
    @SerializedName("images") val images: List<ImageResponseModel>?
)