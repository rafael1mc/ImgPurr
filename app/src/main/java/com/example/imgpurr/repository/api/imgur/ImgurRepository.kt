package com.example.imgpurr.repository.api.imgur

import com.example.imgpurr.repository.entity.GallerySearchResponseModel

interface ImgurRepository {

    suspend fun searchImages(page: Int, search: String): Result<GallerySearchResponseModel>

}